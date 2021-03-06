package hr.fer.zemris.java.hw10.jnotepadpp.actions.editor;

import hr.fer.zemris.java.hw10.jnotepadpp.JNotepadPP;
import hr.fer.zemris.java.hw10.jnotepadpp.local.ILocalizationProvider;
import hr.fer.zemris.java.hw10.jnotepadpp.local.swing.LocalizableAction;

import java.awt.event.ActionEvent;

import javax.swing.JTextArea;
import javax.swing.text.BadLocationException;
import javax.swing.text.Caret;
import javax.swing.text.Document;

/**
 * LowerCaseAction extends {@link LocalizableAction} for changing all
 * characters to lower case.
 * 
 * @author Filip Gulan.
 * @version 1.0
 *
 */
public class LowerCaseAction extends LocalizableAction {

    /** Serial. */
    private static final long serialVersionUID = -1604872428162229622L;
    /** Main JNotepadPP frame. */
    private JNotepadPP observer;

    /**
     * Constructor for LowerCaseAction action class.
     * 
     * @param key
     *            Localization key.
     * @param lp
     *            Localization provider.
     * @param observer
     *            Main JNotepadPP frame.
     */
    public LowerCaseAction(String key, ILocalizationProvider lp,
            JNotepadPP observer) {
        super(key, lp);
        this.observer = observer;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JTextArea currentEditor = observer.getActiveEditor().getEditor();
        Document document = currentEditor.getDocument();
        Caret position = currentEditor.getCaret();

        int len = Math.abs(currentEditor.getCaret().getDot()
                - currentEditor.getCaret().getMark());
        int offset = Math.min(currentEditor.getCaret().getDot(), currentEditor
                .getCaret().getMark());

        try {
            String text = document.getText(offset, len);
            text = lowerCase(text);
            document.remove(offset, len);
            document.insertString(offset, text, null);
            // Reselect text
            position.setDot(offset);
            position.moveDot(offset + len);
            currentEditor.requestFocus();
        } catch (BadLocationException e1) {
            observer.errorMessage(e1);
        }
    }

    /**
     * Invert upper case to lower case in given text.
     * 
     * @param text
     *            Input text.
     * @return Text with inverted case.
     */
    private String lowerCase(String text) {
        int length = text.length();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            char c = text.charAt(i);
            if (Character.isUpperCase(c)) {
                c = Character.toLowerCase(c);
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
