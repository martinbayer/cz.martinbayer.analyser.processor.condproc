package cz.martinbayer.analyser.processor.condproc.gui;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import cz.martinbayer.analyser.procedures.model.ConditionDescriptor;
import cz.martinbayer.analyser.processor.condproc.processor.ConditionProcLogic;

public class ConditionConfigDialog extends TitleAreaDialog {

	private ConditionProcLogic logic;
	private DataBindingContext ctx;

	public ConditionConfigDialog(Shell parent, ConditionProcLogic logic) {
		super(parent);
		this.logic = logic;
		ctx = new DataBindingContext();
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new GridLayout());
		final ConditionsTableViewer viewer = new ConditionsTableViewer(
				container, this.logic, ctx);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.horizontalSpan = 5;
		viewer.getTable().setLayoutData(data);
		Button newCondBtn = new Button(container, SWT.NONE);
		newCondBtn.setText("Add condition");
		newCondBtn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ConditionDescriptor desc = new ConditionDescriptor();
				desc.setEnabled(true);
				viewer.add(desc);
				viewer.getTable().setTopIndex(viewer.getTable().getItemCount());
				viewer.getTable().select(viewer.getTable().getItemCount() - 1);
				viewer.editElement(desc, 0);
			}
		});
		return container;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Configure conditions dialog");
	}

	@Override
	protected Point getInitialSize() {
		return new Point(600, 450);
	}

	@Override
	protected void setShellStyle(int newShellStyle) {
		// TODO Auto-generated method stub
		super.setShellStyle(newShellStyle | SWT.RESIZE | SWT.MAX);
	}
}
