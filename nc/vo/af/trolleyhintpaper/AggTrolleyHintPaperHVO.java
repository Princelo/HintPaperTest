package nc.vo.af.trolleyhintpaper;

public class AggTrolleyHintPaperHVO {

	public TrolleyHintPaperHVO parentVO;

	public TrolleyHintPaperBVO[] childrenVO;

	public TrolleyHintPaperHVO getParentVO() {
		return parentVO;
	}
	public TrolleyHintPaperBVO[] getChildrenVO() {
		return childrenVO;
	}
	public void setParentVO(TrolleyHintPaperHVO parentVO) {
		this.parentVO = parentVO;
	}
	public void setChildrenVO(TrolleyHintPaperBVO[] childrenVO) {
		this.childrenVO = childrenVO;
	}
}