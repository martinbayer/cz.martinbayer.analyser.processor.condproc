package cz.martinbayer.analyser.processor.condproc.gui;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Composite;

import cz.martinbayer.analyser.procedures.model.ConditionDescriptor;
import cz.martinbayer.analyser.processor.condproc.processor.ConditionProcLogic;
import cz.martinbayer.analyser.processors.model.IE4LogsisLog;
import cz.martinbayer.analyser.processors.types.LogProcessor;
import cz.martinbayer.e4.analyser.widgets.AutoDropComboBoxViewerCellEditor;

public class CondFlwProcEditSupport extends EditingSupport {

	private AutoDropComboBoxViewerCellEditor cellEditor;
	private CCombo combo;
	private ConditionProcLogic logic;
	private ConditionsTableViewer tableViewer;

	public CondFlwProcEditSupport(ConditionsTableViewer tableViewer,
			ConditionProcLogic logic, DataBindingContext ctx) {
		super(tableViewer);
		this.tableViewer = tableViewer;
		this.logic = logic;
		initCellEditor();
	}

	private void initCellEditor() {
		cellEditor = new AutoDropComboBoxViewerCellEditor(
				(Composite) getViewer().getControl(), this.tableViewer);
		cellEditor.setLabelProvider(new LabelProvider() {
			@Override
			public String getText(Object element) {
				if (element instanceof LogProcessor) {
					return ((LogProcessor<?>) element).getName();
				}
				return "n/a";
			}
		});
		cellEditor.setContentProvider(new ArrayContentProvider());
		combo = (CCombo) cellEditor.getControl();
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		cellEditor.setInput(logic.getProcessor().getNextProcessors());
		return cellEditor;
	}

	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	@Override
	protected Object getValue(Object element) {
		if (element instanceof ConditionDescriptor) {
			return ((ConditionDescriptor) element).getSelectedProcessor();
		}
		return null;
	}

	@Override
	protected void setValue(Object element, Object value) {
		if (element instanceof ConditionDescriptor) {
			((ConditionDescriptor) element)
					.setSelectedProcessor((LogProcessor<IE4LogsisLog>) value);
		}
	}

}
