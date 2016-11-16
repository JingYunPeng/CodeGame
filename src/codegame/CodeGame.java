package codegame;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import codegame.utils.Frame;

public class CodeGame extends Frame {
	private Text sourceText;
	private Button runButton;
	private Text consoleText;
	private Button nextButton;
	private Text answerText;
	private Label resultLabel;
	private Button commitButton;
	private Button preButton;
	private Text roundText;
	private Button gotoButton;

	public CodeGame() {
		super("代码小游戏", 700, 800);
	}

	@Override
	protected void init() {
		shell.setLayout(new GridLayout(1, false));

		Composite sourceComposite = new Composite(shell, SWT.NONE);

		sourceText = new Text(sourceComposite, SWT.MULTI | SWT.WRAP | SWT.BORDER | SWT.V_SCROLL);
		sourceText.setBounds(0, 0, 650, 270);

		Composite consoleLabelComposite = new Composite(shell, SWT.NONE);
		consoleLabelComposite.setLayout(new FillLayout());

		runButton = new Button(consoleLabelComposite, SWT.PUSH);
		runButton.setText("查看答案");

		preButton = new Button(consoleLabelComposite,SWT.PUSH);
		preButton.setText("上一关");
		
		nextButton = new Button(consoleLabelComposite, SWT.PUSH);
		nextButton.setText("下一关");
		
		roundText = new Text(consoleLabelComposite,SWT.SINGLE|SWT.BORDER);
		//roundText.setText("   ");
		
		gotoButton = new Button(consoleLabelComposite, SWT.PUSH);
		gotoButton.setText("跳关");

//		Composite consoleTextComposite = new Composite(shell, SWT.NONE);
//
//		consoleText = new Text(consoleTextComposite, SWT.MULTI | SWT.WRAP | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
//		consoleText.setBounds(0, 0, 650, 100);
//		consoleText.setEditable(false);

		Composite answerComposite = new Composite(shell, SWT.NONE);
		answerText = new Text(answerComposite, SWT.MULTI | SWT.WRAP | SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		answerText.setBounds(0, 0, 650, 100);

		Composite commitComposite = new Composite(shell, SWT.NONE);
		commitComposite.setLayout(new GridLayout(2,false));
		commitButton = new Button(commitComposite, SWT.PUSH);
		commitButton.setText("提交");
		resultLabel = new Label(commitComposite, SWT.NONE);
		resultLabel.setText(ToolTipConstents.CONSOLE_OUTPUT_TIP);

		Compiler compiler = new Compiler(this);

	}

	public static void main(String[] args) {
		new CodeGame();
	}

	public Text getSourceText() {
		return sourceText;
	}

	public void setSourceText(Text sourceText) {
		this.sourceText = sourceText;
	}

	public Button getRunButton() {
		return runButton;
	}

	public void setRunButton(Button runButton) {
		this.runButton = runButton;
	}

	public Text getConsoleText() {
		return consoleText;
	}

	public void setConsoleText(Text consoleText) {
		this.consoleText = consoleText;
	}

	public Button getNextButton() {
		return nextButton;
	}

	public void setNextButton(Button nextButton) {
		this.nextButton = nextButton;
	}

	public Text getAnswerText() {
		return answerText;
	}

	public void setAnswerText(Text answerText) {
		this.answerText = answerText;
	}

	public Label getResultLabel() {
		return resultLabel;
	}

	public void setResultLabel(Label resultLabel) {
		this.resultLabel = resultLabel;
	}

	public Button getCommitButton() {
		return commitButton;
	}

	public void setCommitButton(Button commitButton) {
		this.commitButton = commitButton;
	}

	public Button getPreButton() {
		return preButton;
	}

	public void setPreButton(Button preButton) {
		this.preButton = preButton;
	}

	public Text getRoundText() {
		return roundText;
	}

	public void setRoundText(Text roundText) {
		this.roundText = roundText;
	}

	public Button getGotoButton() {
		return gotoButton;
	}

	public void setGotoButton(Button gotoButton) {
		this.gotoButton = gotoButton;
	}
	
	
	

}
