package core.map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import core.Main.Engine;
import core.Main.ImageUtils;
import core.gfx.Tileset;
import core.utils.Size;

public class GameMap {

	String tmx_path, tsx_path, orientation, image_Path = "/maps/";
	int tilewidth, tileheight, mapwidth, mapheight;

	private List<Layer> layers = new ArrayList<>();
	private Tileset tiles = new Tileset();

	private BufferedImage image;
	private Graphics2D g;

	private boolean updated = true;

	public GameMap Set(String path) {
		tmx_path = path;
		URL url = GameMap.class.getResource("/");
		String mapsFolder = new File(url.getFile() + "/maps/").getAbsolutePath();
		File file = new File(mapsFolder + "\\" + path);
		tsx_path = mapsFolder + "\\";
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		Document doc = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(file);
		} catch (Exception e) {
		}
		doc.getDocumentElement().normalize();
		Element map = doc.getDocumentElement();
		mapwidth = Integer.parseInt(map.getAttribute("width"));
		mapheight = Integer.parseInt(map.getAttribute("height"));
		tilewidth = Integer.parseInt(map.getAttribute("tilewidth"));
		tileheight = Integer.parseInt(map.getAttribute("tileheight"));
		orientation = map.getAttribute("orientation").equals("orthogonal") ? "orthogonal" : "isometric";

		NodeList layerList = doc.getElementsByTagName("layer");
		Node tileset = map.getElementsByTagName("image").item(0);
		Node image = tileset.getAttributes().item(1);
		if (tileset != null)
			image_Path += (image.getNodeValue());
		generateTileset();
		for (int i = 0; i < layerList.getLength(); i++) {
			Node layers = layerList.item(i);
			Element l = (Element) layers;
			String list = l.getElementsByTagName("data").item(0).getTextContent();
			String[] tokens = list.split(",");

			Layer temp = new Layer().Set(l.getAttribute("name"), mapheight * mapwidth);

			boolean visible = l.getAttribute("visible").equals("0") ? false : true;
			temp.setVisible(visible);
			this.layers.add(temp);
			for (int j = 0; j < mapheight; j++)
				for (int k = 0; k < mapwidth; k++) {
					int tileindex = k + j * mapwidth;
					int index = Integer.parseInt(tokens[tileindex].trim());
					if (index == 0)
						this.layers.get(i).setTile(tileindex, Tile.NULL);
					else
						this.layers.get(i).setTile(tileindex, new Tile().Set(tiles.getTiles()[index - 1], "", true));
				}

		}

		return this;
	}

	private void generateTileset() {
		int tw, th;
		if (orientation.equals("isometric")) {
			tw = tilewidth;
			th = 2 * tileheight;
			tiles.Set(image_Path, tw, th);
		} else {
			tw = tilewidth;
			th = tileheight;
			tiles.Set(image_Path, tw, th);
		}
		image = ImageUtils.createCompatibleImage(new Size(mapwidth * tw, mapheight * th), ImageUtils.ALPHA_BIT_MASKED);
		g = image.createGraphics();
	}

	public void update() {
		updated = true;
	}

	public void render() {
		if (!updated)
			return;
		Engine.time.timerStart();
		if (orientation.equals("isometric"))
			iso_render();
		else
			ortho_render();
		Engine.time.timerStop();
		updated = false;
	}

	private void iso_render() {
		layers.forEach(layer -> {
			if (layer.isVisible())
				for (int j = 0; j < mapheight; j++)
					for (int i = 0; i < mapwidth; i++)
						if (layer.getTile(i + j * mapwidth) != null) {
							BufferedImage s = layer.getTile(i + j * mapwidth).getSprite();
							g.drawImage(s, ((i - j)) * tilewidth / 2 - 16 + (mapwidth * tilewidth / 2),
									(i + j) * tileheight / 2 + 50, null);
						}
		});
	}

	private void ortho_render() {
		layers.forEach(layer -> {
			if (layer.isVisible())
				for (int j = 0; j < mapheight; j++)
					for (int i = 0; i < mapwidth; i++)
						if (layer.getTile(i + j * mapwidth) != null) {
							BufferedImage s = layer.getTile(i + j * mapwidth).getSprite();
							g.drawImage(s, i * tilewidth, j * tileheight, null);
						}
		});
	}

	public BufferedImage getMap() {
		render();
		return image;
	}
}
