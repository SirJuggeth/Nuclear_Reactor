package cp213;

import java.awt.GridLayout;

import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * displays bar meters for current power and temperature *TODO add bar meter for
 * current power and temperature
 * 
 * @author Justin Harrott ID: 141669800
 * 
 * @version 2017 M11 28 17:37:56
 */
@SuppressWarnings("serial")
public class MeterReadingsView extends JComponent {

	/**
	 * The Reactor model.
	 */
	private final Reactor model;

	// ---------------------------------------------------------------
	/**
	 * The view constructor.
	 *
	 * @param newModel
	 *            The Reactor model.
	 */
	public MeterReadingsView(final Reactor model) {

		this.model = model;
		this.layoutView();
		// this.registerListeners();

	}

	// ---------------------------------------------------------------
	/**
	 * Uses the GridLayout to place the labels and buttons.
	 */
	private void layoutView() {
		this.setLayout(new GridLayout(2, 1));

		this.add(new JLabel(""));
		this.add(new JLabel(""));
	}

}
