package core.gui.containers;

import java.awt.Color;
import java.awt.image.BufferedImage;

import core.gui.container;
import core.gui.window;
import core.gui.components.clickable;
import core.gui.components.text;
import core.utils.Size;
import core.utils.Space;

public class UITabContainer extends window {

	static class UITab extends clickable {

		private container contents;
		private container label;
		private UITabContainer parent;

		public UITab() {
			contents.setMargin(new Space(0));
			label = new window().Set(new Size(0, 0));
			label.setBackgroundColor(Color.DARK_GRAY);
		}

		public container getContents() {
			return contents;
		}

		@Override
		public BufferedImage getSprite() {
			return label.getSprite();
		}

		@Override
		public void onClick() {
			parent.activateTab(this);
		}

		@Override
		public void onDrag() {
		}

		@Override
		protected void onFocus() {
		}

		@Override
		public void onRelease() {

		}

		public UITab Set(UITabContainer parent, String label, container contents) {
			this.parent = parent;
			this.contents = contents;
			this.label.addComponent(new text().Set(label));
			return this;
		}

		@Override
		public void update() {
			super.update();
			label.update();
			size = label.getSize();

			label.setBackgroundColor(Color.DARK_GRAY);

			if (hasFocus)
				label.setBackgroundColor(Color.LIGHT_GRAY);

			if (parent.getActiveTab().equals(this))
				label.setBackgroundColor(Color.GRAY);
		}
	}

	private UITab activeTab;
	private container contentContainer;

	private container tabContainer;

	public UITabContainer() {
		tabContainer = new window();
		contentContainer = new window();

		setMargin(new Space(0));

		tabContainer.setPadding(new Space(0));
		tabContainer.setMargin(new Space(0));
		contentContainer.setMargin(new Space(0));
		contentContainer.setBackgroundColor(Color.GRAY);

		addComponent(tabContainer);
		addComponent(contentContainer);
		tabContainer.addComponent(null);// new UIHideButton(this, contentContainer));
	}

	private void activateTab(UITab uiTab) {
		activeTab = uiTab;
		contentContainer.clear();
		contentContainer.addComponent(uiTab.getContents());
	}

	public void addTab(String label, container contents) {
		UITab tab = new UITab().Set(this, label, contents);
		tabContainer.addComponent(tab);

		if (activeTab == null)
			activateTab(tab);
	}

	public UITab getActiveTab() {
		return activeTab;
	}

	@Override
	public UITabContainer Set(Size windowSize) {
		tabContainer = tabContainer.Set(windowSize);
		contentContainer = contentContainer.Set(windowSize);
		return this;
	}
}
