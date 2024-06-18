package ui;

import javax.swing.JRadioButton;

/**
 * Classe helper para guardar um valor, util para identificar radio button entre eventos
 * @author Andre Rios
 *
 */
public class JRadioButtonCustom extends JRadioButton{
	public JRadioButtonCustom(String string) {
		super(string);
	}

	public String valor;
}
