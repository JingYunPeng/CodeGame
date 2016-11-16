package codegame;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import codegame.utils.ResourceUtil;
import codegame.utils.compile.DynaCompUtil;

enum Cycle{
	READSOURCE,COMMIT;
}

public class Compiler {
	private int round = 0;
	private Cycle cycle;
	
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
	
	public Compiler(CodeGame game) {
		nextButton = game.getNextButton();
		sourceText = game.getSourceText();
		consoleText = game.getConsoleText();
		runButton = game.getRunButton();
		answerText = game.getAnswerText();
		resultLabel = game.getResultLabel();
		commitButton = game.getCommitButton();
		preButton = game.getPreButton();
		roundText = game.getRoundText();
		gotoButton = game.getGotoButton();
		init();
	}

	private void init() {

		nextButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				round ++;
				setCycle(Cycle.READSOURCE);
			}
		});
		
		preButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				round --;
				setCycle(Cycle.READSOURCE);
			}
		});
		
		gotoButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				round = Integer.parseInt(roundText.getText());
				setCycle(Cycle.READSOURCE);
			}
		});
		
		commitButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setCycle(Cycle.COMMIT);
			}
		});
	}

	public Cycle getCycle() {
		return cycle;
	}

	public void setCycle(Cycle cycle) {
		this.cycle = cycle;
		switch (cycle) {
		case READSOURCE:
			doReadSource();
			break;
		case COMMIT:
			doCommit();
			break;
		default:
			break;
		}
	}
	
	

	private void doCommit() {
		try {
			System.err.println(getResult());
			doChangeLabe(check(getResult()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private boolean check(String result){
		String answer = answerText.getText();
		if(result.startsWith("Code:compiler")){
			return answer.equals("err");
		}else if(result.equals("true")){
			return answer.equals("t");
		}else if(result.equals("false")){
			return answer.equals("f");
		}
		return result.equals(answer);
	}
	
	private void doChangeLabe(boolean equals) {
		if(equals){
			System.out.println(true);
			resultLabel.setText(ToolTipConstents.YOU_WIN);
		}else{
			System.out.println(false);
			resultLabel.setText(ToolTipConstents.YOU_LOSE);
		}
		
	}

	private String getResult() throws Exception {
		return DynaCompUtil.getAnswer("Fun"+String.format("%03d", round), getSource(round));
	}

	private void doReadSource() {
		sourceText.setText(getSource(round));
		answerText.setText("");
		resultLabel.setText(ToolTipConstents.CONSOLE_OUTPUT_TIP);
	}
	
	

	private String getSource(int round) {
		return ResourceUtil.getContent(this.getClass(), String.format("%03d", round));
	}
	
//	public static void main(String[] args) {
//		System.out.println(String.format("%03d", 0));
//	}
	

}
