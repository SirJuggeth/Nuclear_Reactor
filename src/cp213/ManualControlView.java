package cp213;

import java.awt.BorderLayout;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * A GUI that provides controls and status info for Reactor simulator
 * 
 * @author Justin Harrott ID: 141669800
 * 
 * @version 2017 M11 25 14:33:34
 */

@SuppressWarnings("serial")
public class ManualControlView extends JPanel {

	/**
	 * The sub-view containing the rod controls.
	 */
	private RodControlView rodControlls = null;
	/**
	 * The sub-view containing the status information.
	 */
	private StatusView statuses = null;
	/**
	 * The sub-view containing the power and temperature meters.
	 */
	private MeterReadingsView meters = null;

	// ---------------------------------------------------------------
	/**
	 * View constructor that instantiates all of the sub-views of the Reactor
	 * simulation.
	 *
	 * @param model
	 *            The Reactor model.
	 */
	public ManualControlView(Reactor model) {

		this.rodControlls = new RodControlView(model);
		this.statuses = new StatusView(model);
		this.meters = new MeterReadingsView(model);
		this.layoutView();
	}

	/**
	 * Lays out the sub-views of the Reactor simulator in a Border layout.
	 */
	private void layoutView() {

		this.setLayout(new BorderLayout());
		this.add(this.rodControlls, BorderLayout.NORTH);

		final JPanel south = new JPanel();
		south.setLayout(new BoxLayout(south, BoxLayout.X_AXIS));
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		south.setBorder(new EmptyBorder(10, 10, 10, 10));
		south.add(this.statuses);
		south.add(this.meters);
		this.add(south, BorderLayout.SOUTH);
	}

}
