package cp213;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Displays buttons to control rod height updates the rod height that increments
 * by 1.
 * 
 * @author Justin Harrott ID: 141669800
 * 
 * @version 2017 M11 28 17:41:34
 */
@SuppressWarnings("serial")
public class RodControlView extends JPanel {

	// ---------------------------------------------------------------
	/**
	 * An inner class that uses an ActionListener to access the buttons. It sets the
	 * model values when the button is pressed.
	 */
	private class RodButtonListener implements ActionListener {
		/**
		 * Determines whether values are incremented (+) or decremented (-).
		 */
		private int direction = 0;

		public RodButtonListener(final int direction) {
			this.direction = direction;
		}

		@Override
		public void actionPerformed(final ActionEvent evt) {
			// TODO fix this
			if (direction < 0) {
				RodControlView.this.model.raiseRods();
			} else if (direction > 0) {
				RodControlView.this.model.lowerRods();
			}
			// RodControlView.this.model.setBase(RodControlView.this.model.getBase() +
			// this.direction);
		}
	}

	// -------------------------------------------------------------------------------
	/**
	 * An inner class the updates the base and hypotenuse labels whenever the
	 * model's base attribute is updated.
	 */
	private class RodListener implements PropertyChangeListener {

		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			// TODO fix this
			// RodControlView.this.base.setText(RodControlView.f.format(RodControlView.this.model.getBase()));
			// RodControlView.this.hypo.setText(RodControlView.f.format(RodControlView.this.model.getHypotenuse()));
		}
	}

	// -------------------------------------------------------------------------------
	/**
	 * An inner class that uses an ActionListener to access the buttons. It sets the
	 * model values when the button is pressed.
	 */
	private class EmergencyButtonListener implements ActionListener {

		@Override
		public void actionPerformed(final ActionEvent evt) {
			// TODO fix this
			// RodControlView.this.model.setHeight(RodControlView.this.model.getHeight() +
			// this.direction);
			RodControlView.this.model.dropRods();
		}
	}

	// -------------------------------------------------------------------------------
	/**
	 * An inner class the updates the height and hypotenuse labels whenever the
	 * model's height attribute is updated.
	 */
	private class EmergencyListener implements PropertyChangeListener {

		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			// TODO fix this
			// RodControlView.this.height.setText(RodControlView.f.format(RodControlView.this.model.getHeight()));
			// RodControlView.this.hypo.setText(RodControlView.f.format(RodControlView.this.model.getHypotenuse()));
		}
	}

	// -------------------------------------------------------------------------------
	/**
	 * Withdraws rods from reactor core by 1.
	 */
	private final JButton rodWithdraw = new JButton("-");
	/**
	 * Goes to next tick without moving rods.
	 */
	// private final JButton noChange = new JButton("Don't Change");
	/**
	 * Inserts rods into reactor core by 1.
	 */
	private final JButton rodInsert = new JButton("+");
	/**
	 * Decrements height by 1.
	 */
	private final JButton emergencyDrop = new JButton("!* Emergency Rod Drop *!");
	{
		emergencyDrop.setBackground(Color.RED);
	}

	/**
	 * The Reactor model.
	 */
	private final Reactor model;

	// ---------------------------------------------------------------
	/**
	 * The view constructor.
	 *
	 * @param newModel
	 *            The right triangle model.
	 */
	public RodControlView(final Reactor newModel) {
		this.model = newModel;
		this.layoutView();
		this.registerListeners();
	}

	// ---------------------------------------------------------------
	/**
	 * Uses the GridLayout to place the labels and buttons.
	 */
	private void layoutView() {
		this.setLayout(new GridLayout(3, 3));

		this.add(new JLabel());
		((JComponent) this.add(new JLabel("<html><u>Rod Control</u></html>")))
				.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.add(new JLabel());

		this.add(this.rodInsert);
		((JComponent) this.add(new JLabel("Insert Into Core"))).setBorder(new EmptyBorder(10, 10, 10, 10));
		this.add(this.emergencyDrop);
		// this.add(new JLabel());

		// this.add(this.noChange);
		// ((JComponent) this.add(new JLabel("Leave Rods Alone"))).setBorder(new
		// EmptyBorder(10, 10, 10, 10));
		// this.add(this.emergencyDrop);

		this.add(this.rodWithdraw);
		((JComponent) this.add(new JLabel("Retract From Core"))).setBorder(new EmptyBorder(10, 10, 10, 10));
		this.add(new JLabel("<html>Immediate Rod Insertion<br>....Plant Will Shutdown....</html>",
				SwingConstants.CENTER));

	}

	// ---------------------------------------------------------------
	/**
	 * Assigns listeners to the view widgets and the model.
	 */
	private void registerListeners() {
		// Add widget listeners.
		this.rodInsert.addActionListener(new RodButtonListener(1));
		this.rodWithdraw.addActionListener(new RodButtonListener(-1));
		// this.noChange.addActionListener(new RodButtonListener(0));
		this.emergencyDrop.addActionListener(new EmergencyButtonListener());
		// Add model listeners.
		this.model.addPropertyChangeListener(Reactor.ROD_HEIGHT_CHANGE, new RodListener());
		this.model.addPropertyChangeListener(Reactor.ROD_DROP_CHANGE, new EmergencyListener());
	}

	// ---------------------------------------------------------------
}
