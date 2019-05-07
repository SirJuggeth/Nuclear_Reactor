package cp213;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class AutoPilotView extends JPanel {

	/**
	 * The sub-view containing the status information.
	 */
	private StatusView statuses = null;

	/**
	 * The sub-view containing the power and temperature meters.
	 */
	// private MeterView meters = null;

	// ---------------------------------------------------------------
	/**
	 * The view constructor.
	 *
	 * @param newModel
	 *            The right triangle model.
	 */
	public AutoPilotView(final Reactor model) {
		this.statuses = new StatusView(model);
		// this.meters = new MeterView(model);
		this.layoutView();
	}

	/**
	 * Lays out the sub-views of the Reactor simulator in a Border layout.
	 */
	private void layoutView() {
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.add(this.statuses, BorderLayout.NORTH);
		// this.add(this.meters);

	}

}
