package cz.martinbayer.analyser.processor.condproc.gui;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.TableViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;

import cz.martinbayer.analyser.procedures.ProcedureFactory;
import cz.martinbayer.analyser.procedures.ProcedureUtils;
import cz.martinbayer.analyser.procedures.model.ConditionDescriptor;
import cz.martinbayer.analyser.processor.condproc.processor.ConditionProcLogic;

public class ConditionsTableViewer extends TableViewer {

	private Table table;
	private DataBindingContext ctx;
	private ConditionProcLogic logic;

	public ConditionsTableViewer(Composite parent, ConditionProcLogic logic,
			DataBindingContext ctx) {
		super(new Table(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.V_SCROLL
				| SWT.H_SCROLL));
		this.ctx = ctx;
		this.logic = logic;
		setContentProvider(ArrayContentProvider.getInstance());
		initTable();
		initializeColumns(this.ctx);
		initData();
	}

	private void initTable() {
		table = getTable();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
	}

	private void initializeColumns(DataBindingContext ctx) {
		// first column is used to enable the condition
		initEnableConditionColumn();
		initProceduresColumn(ctx);
		initParamColumn();
		initOperatorColumn();
		initOperandColumns();
		initFollowingProcessorsColumn();
	}

	private void initFollowingProcessorsColumn() {
		TableColumn tabColumn = new TableColumn(getTable(), SWT.NONE);
		tabColumn.setText("Processor");
		TableViewerColumn column = new TableViewerColumn(this, tabColumn);
		column.setLabelProvider(new CondFlwProcLabelProvider());
		column.setEditingSupport(new CondFlwProcEditSupport(this, logic, ctx));
		column.getColumn().pack();
	}

	@Override
	public void add(Object element) {
		if (element instanceof ConditionDescriptor) {
			logic.addCondDescriptorToModel((ConditionDescriptor) element);
			super.add(element);
		}
	}

	/**
	 * Initialize data for table. Use the collection of procedures in the logic
	 * instance to initialize an input of the table. When you add the item to
	 * the table, it must be also added to the collection of procedures in model
	 */
	private void initData() {
		setInput(logic.getCondDescriptors());
	}

	private void initEnableConditionColumn() {
		TableColumn tabColumn = new TableColumn(getTable(), SWT.NONE);
		tabColumn.setText("Enabled");
		TableViewerColumn column = new TableViewerColumn(this, tabColumn);
		column.setLabelProvider(new CondEnableLabelProvider());
		column.setEditingSupport(new CondEnableEditSupport(this));
		column.getColumn().pack();
	}

	private void initProceduresColumn(DataBindingContext ctx) {
		TableColumn tabColumn = new TableColumn(getTable(), SWT.NONE);
		tabColumn.setText("Procedure");
		TableViewerColumn column = new TableViewerColumn(this, tabColumn);
		column.setLabelProvider(new CondProcLabelProvider());
		column.setEditingSupport(new CondProcEditSupport(this, ProcedureFactory
				.getProcedures(), ctx));
		column.getColumn().pack();
	}

	private void initParamColumn() {
		TableColumn tabColumn = new TableColumn(getTable(), SWT.NONE);
		tabColumn.setText("Parameter");
		TableViewerColumn column = new TableViewerColumn(this, tabColumn);
		column.setLabelProvider(new CondParamLabelProvider());
		column.setEditingSupport(new CondParamEditSupport(column.getViewer()));
		column.getColumn().pack();
	}

	private void initOperatorColumn() {
		TableColumn tabColumn = new TableColumn(getTable(), SWT.NONE);
		tabColumn.setText("Operator");
		TableViewerColumn column = new TableViewerColumn(this, tabColumn);
		column.setLabelProvider(new CondOperatorLabelProvider());
		column.setEditingSupport(new CondOperatorEditSupport(column.getViewer()));
		column.getColumn().pack();
	}

	private void initOperandColumns() {
		int operandCount = ProcedureUtils.getMaxOperands(ProcedureFactory
				.getProcedures());
		for (int i = 0; i < operandCount; i++) {
			TableColumn tabColumn = new TableColumn(getTable(), SWT.NONE);
			tabColumn.setText("Operand " + (i + 1));
			TableViewerColumn column = new TableViewerColumn(this, tabColumn);
			column.setLabelProvider(new CondOperandLabelProvider(i));
			column.setEditingSupport(new CondOperandEditSupport(column
					.getViewer(), i));
			column.getColumn().pack();
		}
	}
}
