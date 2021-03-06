package priv.bajdcc.LALR1.grammar.tree;

import java.util.HashSet;

import priv.bajdcc.LALR1.grammar.codegen.ICodegen;
import priv.bajdcc.LALR1.grammar.runtime.RuntimeInst;
import priv.bajdcc.LALR1.grammar.semantic.ISemanticRecorder;
import priv.bajdcc.LALR1.grammar.tree.closure.ClosureScope;
import priv.bajdcc.LALR1.grammar.tree.closure.IClosureScope;

/**
 * 【语义分析】函数定义表达式
 *
 * @author bajdcc
 */
public class ExpFunc extends ClosureScope implements IExp {

	/**
	 * 调用函数
	 */
	private Function func = null;

	/**
	 * 闭包
	 */
	private HashSet<Object> closure = null;

	public Function getFunc() {
		return func;
	}

	public void setFunc(Function func) {
		this.func = func;
	}

	@Override
	public boolean isConstant() {
		return false;
	}

	@Override
	public boolean isEnumerable() {
		return func.isEnumerable();
	}

	public void genClosure() {
		func.addClosure(this);
		closure = getClosure();
	}

	@Override
	public IExp simplify(ISemanticRecorder recorder) {
		return this;
	}

	@Override
	public void analysis(ISemanticRecorder recorder) {
		func.analysis(recorder);
	}

	@Override
	public void genCode(ICodegen codegen) {
		if (closure == null) {
			codegen.genCode(RuntimeInst.ipushz);
		} else {
			for (Object obj : closure) {
				codegen.genCode(RuntimeInst.ipush, codegen.genDataRef(obj));
			}
			codegen.genCode(RuntimeInst.ipush, closure.size());
		}
		codegen.genCodeWithFuncWriteBack(RuntimeInst.ipush, codegen.getFuncIndex(func));
		codegen.genCode(RuntimeInst.ildfun);
	}

	@Override
	public String toString() {
		return print(new StringBuilder());
	}

	@Override
	public String print(StringBuilder prefix) {
		return func.print(prefix);
	}

	@Override
	public void addClosure(IClosureScope scope) {

	}

	@Override
	public void setYield() {
		
	}
}
