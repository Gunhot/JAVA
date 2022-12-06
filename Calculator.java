import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class Calculator extends JFrame{
	
	private JTextField inputSpace;
	private String num = "";
	private ArrayList<String> equation = new ArrayList<String>();
	public Calculator() {
		setLayout(null);
		
		inputSpace = new JTextField();
		inputSpace.setEditable(false);
		inputSpace.setBackground(Color.WHITE);
		inputSpace.setHorizontalAlignment(JTextField.RIGHT);
		inputSpace.setFont(new Font("Arial", Font.BOLD, 50));
		inputSpace.setBounds(10, 12, 380, 111);
		add(inputSpace);	

		JPanel JButtons = new JPanel();
		JButtons.setBounds(10, 135, 380, 333);
		JButtons.setLayout(new GridLayout(5, 5, 10, 10));
		JButton factorial = new JButton("x!");
		factorial.addActionListener(new JButtonListener());
		JButtons.add(factorial);
		JButton leftParenth = new JButton("(");
		leftParenth.addActionListener(new JButtonListener());
		JButtons.add(leftParenth);
		JButton rightParenth  = new JButton(")");
		rightParenth.addActionListener(new JButtonListener());
		JButtons.add(rightParenth);
		JButton mod = new JButton("%");
		mod.addActionListener(new JButtonListener());
		JButtons.add(mod);
		JButton AC = new JButton("AC");
		AC.addActionListener(new JButtonListener());
		JButtons.add(AC);
		//
		JButton ln = new JButton("ln");
		ln.addActionListener(new JButtonListener());
		JButtons.add(ln);
		JButton num7 = new JButton("7");
		num7.addActionListener(new JButtonListener());
		JButtons.add(num7);
		JButton num8 = new JButton("8");
		num8.addActionListener(new JButtonListener());
		JButtons.add(num8);
		JButton num9 = new JButton("9");
		num9.addActionListener(new JButtonListener());
		JButtons.add(num9);
		JButton divide = new JButton("/");
		divide.addActionListener(new JButtonListener());
		JButtons.add(divide);
		//
		JButton log = new JButton("log");
		log.addActionListener(new JButtonListener());
		JButtons.add(log);
		JButton num4 = new JButton("4");
		num4.addActionListener(new JButtonListener());
		JButtons.add(num4);
		JButton num5 = new JButton("5");
		num5.addActionListener(new JButtonListener());
		JButtons.add(num5);
		JButton num6 = new JButton("6");
		num6.addActionListener(new JButtonListener());
		JButtons.add(num6);
		JButton multiply = new JButton("*");
		multiply.addActionListener(new JButtonListener());
		JButtons.add(multiply);
		//
		JButton root = new JButton("√");
		root.addActionListener(new JButtonListener());
		JButtons.add(root);
		JButton num1 = new JButton("1");
		num1.addActionListener(new JButtonListener());
		JButtons.add(num1);
		JButton num2 = new JButton("2");
		num2.addActionListener(new JButtonListener());
		JButtons.add(num2);
		JButton num3 = new JButton("3");
		num3.addActionListener(new JButtonListener());
		JButtons.add(num3);
		JButton minus = new JButton("-");
		minus.addActionListener(new JButtonListener());
		JButtons.add(minus);
		//
		JButton power = new JButton("x^y");
		power.addActionListener(new JButtonListener());
		JButtons.add(power);
		JButton num0 = new JButton("0");
		num0.addActionListener(new JButtonListener());
		JButtons.add(num0);
		JButton dot = new JButton(".");
		dot.addActionListener(new JButtonListener());
		JButtons.add(dot);
		JButton equal = new JButton("=");
		equal.addActionListener(new JButtonListener());
		JButtons.add(equal);
		JButton plus = new JButton("+");
		plus.addActionListener(new JButtonListener());
		JButtons.add(plus);
		
		add(JButtons);

		setTitle("계산기");
		setVisible(true);
		setSize(410, 520);
		setLocationRelativeTo(null); 
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public static void main(String[] args) {
		new Calculator();
	}
	class JButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String operation = e.getActionCommand();
			
			if (operation.equals("AC")) {
				inputSpace.setText("");
				num = "";
				equation.clear();
				Postfix.clear();
			} 
			
			else if (operation.equals("=")) {
			Partitioning(inputSpace.getText());
			
			String result = String.format("%.6f",  calculate());
			inputSpace.setText("" + result);
			num = "";
			result = "";
			equation.clear();
			Postfix.clear();
			} 
			
			else {
				if (e.getActionCommand() == "x^y") {
				inputSpace.setText(inputSpace.getText() + '^');
				}
				else if(e.getActionCommand() == "x!") {
				inputSpace.setText(inputSpace.getText() + '!');					
				}
				else {
				inputSpace.setText(inputSpace.getText() + e.getActionCommand());
				}
			}
		}
	}
	private void Partitioning(String inputText) {
		for (int i = 0; i < inputText.length(); i++) {
			char ch = inputText.charAt(i);
			
			if (ch == '/'||ch == '%'||ch == ')'||ch == '('||ch == '!'||ch == '-' || ch == '+' || ch == '*' || ch == '/' || ch == '^' || ch == '√' || ch == '.') {
				if(ch != '(' && ((i>0) && inputText.charAt(i-1) != ')') && ch != '√') {
					equation.add(num);
					
					num = "";
				}
				
				equation.add(ch + "");
			}
			else if(ch == 'l'){
				equation.add(num);
				
				num = "";
				
				i++;
				ch = inputText.charAt(i);
				if(ch == 'o') {
					i++;
					equation.add("log");
				}
				else {
					equation.add("ln");
				}
				
			}
			else {
			
				num = num + ch;
			}
		}
		
		equation.add(num);
	}


	private ArrayList<String> StackOperator = new ArrayList<String>();
	private String popOperator() {
		String ReturnString = StackOperator.get(StackOperator.size()-1);
		StackOperator.remove(StackOperator.size()-1);
		
		return ReturnString;
	}
	private void pushOperator(String newOperator) {
		StackOperator.add(newOperator);
	}
	
	
	private int ispGrade(String operator) {
		switch(operator) {
		case ".": 
			return 7;
			
		case "(":
			return 0;
		case ")": 
			return 5;
			
		case "ln": 
		case "log":
			return 4;
			
		case "^": 
		case "!": 
		case "√": 
		case "%": 
			return 3;
			
		case "/": 
		case "*": 
			return 2;
			
		case "+": 
		case "-": 
			return 1;
		default:
			break;
		}
		return -1;
	}
	private int icpGrade(String operator) {
		switch(operator) {
		case ".": 
			return 7;
			
		case "(":
			return 6;
		case ")": 
			return 5;
			
		case "ln": 
		case "log":
		case "√": 			
			return 4;
			
		case "^": 
		case "!": 
		case "%": 
			return 3;
			
		case "/": 
		case "*": 
			return 2;
			
		case "+": 
		case "-": 
			return 1;
		default:
			break;
		}
		return -1;
	}
	public static boolean isNumeric(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	private ArrayList<String> Postfix = new ArrayList<String>();
	private void postfix() {
		
		for (String token : equation) {
		
			if(isNumeric(token)) {
				Postfix.add(token);
			}
			else if(token.equals(")")) {
				while(StackOperator.size() > 0 && !(StackOperator.get(StackOperator.size() - 1).equals("("))) {
					Postfix.add(popOperator());
				}
				popOperator();
			}
			else{
				
				while(StackOperator.size() > 0 && (icpGrade(token) <= ispGrade(StackOperator.get(StackOperator.size()-1)))){
					Postfix.add(popOperator());
				}
				pushOperator(token);
			}
		}

		while(StackOperator.size() > 0)Postfix.add(popOperator());
	}
	
	private ArrayList<Double> StackEval = new ArrayList<Double>();
	private double popEval() {
		double ReturnValue= StackEval.get(StackEval.size()-1);
		StackEval.remove(StackEval.size()-1);
		return ReturnValue;
	}
	
	private void pushEval(double newOperand) {
		StackEval.add(newOperand);
	}
	
	
	public double calculate() {
		double op1, op2, n;
		postfix();
		for (String token : Postfix) {
			op1 = op2 = n = 1.0;
			if(isNumeric(token)) {
				pushEval(Integer.parseInt(token));
			}
			else if(token.equals("!")){
				op2 = popEval();
				for ( int i = 1; i <= op2; i++) {
					n *= i;
				}
				pushEval(n);
			}
			else if(token.equals("ln")){
				op2 = popEval();
				n = Math.log(op2);
				pushEval(n);
			}
			else if(token.equals("log")){
				op2 = popEval();
				n = Math.log10(op2);
				pushEval(n);
			}
			else if(token.equals("√")){
				op2 = popEval();
				n = Math.sqrt(op2);
				pushEval(n);
			}
			else if(token.equals("^")){
				op2 = popEval();
				op1 = popEval();
				for (int i= 0; i < op2; i++) {
					n *= op1;
				}
				pushEval(n);				
			}
			else if(token.equals("%")){
				op2 = popEval();
				op1 = popEval();
				n = op1 % op2;
				pushEval(n);	
			}
			else if(token.equals("/")){
				op2 = popEval();
				op1 = popEval();
				n = op1 / op2;
				pushEval(n);	
			}
			else if(token.equals("*")){
				op2 = popEval();
				op1 = popEval();
				n = op1 * op2;
				pushEval(n);	
			}
			else if(token.equals("-")){
				op2 = popEval();
				op1 = popEval();
				n = op1 - op2;
				pushEval(n);	
			}
			else if(token.equals("+")){
				op2 = popEval();
				op1 = popEval();
				n = op1 + op2;
				pushEval(n);	
			}
			else if(token.equals(".")){
				op2 = popEval();
				op1 = popEval();
				int length = (int)(Math.log10(op2) + 1);
				for (int i= 0; i < length; i++) {
					op2 /= 10;
				}
				n = op1 + op2;
				pushEval(n);	
			}
			else {
				
			}
			
		}
		return popEval();
	}
	
}

