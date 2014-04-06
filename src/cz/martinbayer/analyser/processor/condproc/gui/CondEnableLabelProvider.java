package cz.martinbayer.analyser.processor.condproc.gui;

import org.eclipse.jface.viewers.OwnerDrawLabelProvider;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.TableItem;

import cz.martinbayer.analyser.procedures.model.ConditionDescriptor;
import cz.martinbayer.utils.gui.SWTUtils;

public class CondEnableLabelProvider extends OwnerDrawLabelProvider {

	private static final Image CHECKED = SWTUtils.getImage("images",
			"checked.gif", CondEnableLabelProvider.class);
	private static final Image UNCHECKED = SWTUtils.getImage("images",
			"unchecked.gif", CondEnableLabelProvider.class);

	@Override
	protected void measure(Event event, Object element) {
	}

	public Image getImage(Object element) {
		if (element instanceof ConditionDescriptor) {
			return ((ConditionDescriptor) element).isEnabled() ? CHECKED
					: UNCHECKED;
		}
		return null;
	}

	@Override
	protected void paint(Event event, Object element) {
		Image img = getImage(element);

		if (img != null) {
			Rectangle bounds = ((TableItem) event.item).getBounds(event.index);
			Rectangle imgBounds = img.getBounds();
			bounds.width /= 2;
			bounds.width -= imgBounds.width / 2;
			bounds.height /= 2;
			bounds.height -= imgBounds.height / 2;

			int x = bounds.width > 0 ? bounds.x + bounds.width : bounds.x;
			int y = bounds.height > 0 ? bounds.y + bounds.height : bounds.y;

			event.gc.drawImage(img, x, y);
		}
	}
}
