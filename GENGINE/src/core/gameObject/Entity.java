package core.gameObject;

import java.awt.image.BufferedImage;

import core.gfx.AnimatedSprite;
import core.gfx.Sprite;
import core.utils.Position;

public abstract class Entity extends gameobject {

	private Sprite sprite;
	private AnimatedSprite animation;
	private String name;
	private Position position;

	public Entity() {
		super();
	}

	public Entity Set(Sprite sprite, String name, Position position) {
		this.sprite = sprite;
		this.name = name;
		this.position = position;

		return this;
	}

	public void update() {
		if (animation!=null) animation.update();
	}

	public String getName() {
		return name;
	}

	public Position getPosition() {
		return position;

	}

	public BufferedImage getSprite() {
		return sprite.getSprite();
	}

	public void setSprite(Sprite sprite) {
		this.sprite = sprite;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public AnimatedSprite getAnimation() {
		return animation;
	}

	public void setAnimation(AnimatedSprite animation) {
		this.animation = animation;
	}

}
