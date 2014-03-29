package cz.martinbayer.analyser.processor.condproc.gui;

import java.util.Collection;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.UpdateValueStrategy;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxViewerCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Composite;

import cz.martinbayer.analyser.procedures.IProcedure;
import cz.martinbayer.analyser.procedures.model.ConditionDescriptor;
import cz.martinbayer.e4.analyser.widgets.AutoDropComboBoxViewerCellEditor;

public class CondProcEditSupport extends EditingSupport {

	private ConditionsTableViewer tableViewer;
	private ComboBoxViewerCellEditor cellEditor;
	private DataBindingContext ctx;
	private CCombo combo;
	private boolean bind = false;
	private Collection<IProcedure> procedures;

	public CondProcEditSupport(ConditionsTableViewer tableViewer,
			Collection<IProcedure> procedures, DataBindingContext ctx) {
		super(tableViewer);
		this.procedures = procedures;
		this.ctx = ctx;
		this.tableViewer = tableViewer;
		cellEditor = new AutoDropComboBoxViewerCellEditor(
				(Composite) getViewer().getControl(), this.tableViewer);

		combo = (CCombo) cellEditor.getControl();
		cellEditor.setContentProvider(new ArrayContentProvider());
		cellEditor.setLabelProvider(new CondProcLabelProvider());
		cellEditor.setInput(this.procedures);
	}

	@Override
	protected CellEditor getCellEditor(Object element) {
		return cellEditor;
	}

	@Override
	protected boolean canEdit(Object element) {
		return true;
	}

	@Override
	protected Object getValue(Object element) {
		if (element instanceof ConditionDescriptor) {
			return ((ConditionDescriptor) element).getSelectedProcedure();
		}
		return null;
	}

	@Override
	protected void setValue(Object element, Object value) {
		if (element instanceof ConditionDescriptor) {
			((ConditionDescriptor) element)
					.setSelectedProcedure((IProcedure) value);
		}
	}

	static class StringToProcedureUpdateStrategy extends UpdateValueStrategy {
		public StringToProcedureUpdateStrategy(final IProcedure[] procedures) {
			setConverter(new IConverter() {

				@Override
				public Object getToType() {
					return IProcedure.class;
				}

				@Override
				public Object getFromType() {
					return String.class;
				}

				@Override
				public Object convert(Object fromObject) {
					if (fromObject != null) {
						for (IProcedure proc : procedures) {
							if (proc.getName().equals(fromObject)) {
								return proc;
							}
						}
					}
					return null;
				}
			});
		}
	}
}
