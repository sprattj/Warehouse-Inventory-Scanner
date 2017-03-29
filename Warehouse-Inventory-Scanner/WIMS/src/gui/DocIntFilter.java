package gui;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

public class DocIntFilter extends DocumentFilter {

	private int minInputLength;
	private int maxInputLength;

	public DocIntFilter(int minInputLength, int maxInputLength) {
		this.minInputLength = minInputLength;
		this.maxInputLength = maxInputLength;
	}
	
	@Override
	public void insertString(FilterBypass fb, int offset, String string,
			AttributeSet attr) throws BadLocationException {

		Document doc = fb.getDocument();
		StringBuilder sb = new StringBuilder();
		sb.append(doc.getText(0, doc.getLength()));
		sb.insert(offset, string);

		if (test(sb.toString())) {
			super.insertString(fb, offset, string, attr);
		} else {
			
		}
	}

	private String getTextForBadInput(String text)
	{
		String error;
		if(!isCorrectLength(text))
			error = "Numbers entered must be between " + this.minInputLength 
			+ " and " + maxInputLength + " digits.";
		else
			error = "Only numbers can be entered in this field.";
		return error;
	}
	
	public boolean isCorrectLength(String text)
	{
		return text.length() > this.minInputLength
				&& text.length() <= this.maxInputLength;
	}
	private boolean test(String text) {
		try {
			Integer.parseInt(text);
			if (isCorrectLength(text))
				return true;
			return false;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	public void replace(FilterBypass fb, int offset, int length, String text,
			AttributeSet attrs) throws BadLocationException {

		Document doc = fb.getDocument();
		StringBuilder sb = new StringBuilder();
		sb.append(doc.getText(0, doc.getLength()));
		sb.replace(offset, offset + length, text);

		if (test(sb.toString())) {
			super.replace(fb, offset, length, text, attrs);
		} else {
			
		}

	}

	@Override
	public void remove(FilterBypass fb, int offset, int length)
			throws BadLocationException {
		Document doc = fb.getDocument();
		StringBuilder sb = new StringBuilder();
		sb.append(doc.getText(0, doc.getLength()));
		sb.delete(offset, offset + length);

		if (test(sb.toString())) {
			super.remove(fb, offset, length);
		} else {
			
		}

	}
}