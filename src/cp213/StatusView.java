package cp213;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 * Displays values for current power and temperature and average power.\
 * 
 * @author Justin Harrott ID: 141669800
 * 
 * @version 2017 M11 28 17:21:51
 */
@SuppressWarnings("serial")
public class StatusView extends JPanel {

	// ---------------------------------------------------------------
	/**
	 * An inner class that uses an ActionListener to set the model's status to
	 * finished.
	 * 
	 */
	private class FinishButtonListener implements ActionListener {

		@Override
		public void actionPerformed(final ActionEvent evt) {
			model.quit();
		}
	}

	// -------------------------------------------------------------------------------
	/**
	 * An inner class the updates the rod insertion length label whenever the
	 * model's height attribute is updated.
	 */
	private class RodListener implements PropertyChangeListener {

		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			// TODO fix this
			StatusView.this.rodLength.setText(StatusView.intFormat.format(StatusView.this.model.getRodsHeight()));

		}
	}

	// -------------------------------------------------------------------------------
	/**
	 * An inner class the updates the power output label whenever the model's power
	 * output attribute is updated.
	 */
	private class PowerListener implements PropertyChangeListener {

		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			// TODO fix this
			StatusView.this.powerOutput.setText(StatusView.decimalFormat.format(StatusView.this.model.getPower()));
		}
	}

	// -------------------------------------------------------------------------------
	/**
	 * An inner class the updates the average power label whenever the model's power
	 * average attribute is updated.
	 */
	private class PowerAverageListener implements PropertyChangeListener {

		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			// TODO fix this
			StatusView.this.powerAv.setText(StatusView.decimalFormat.format(StatusView.this.model.getAveragePower()));
		}
	} // -------------------------------------------------------------------------------

	/**
	 * An inner class the updates the temperature label whenever the model's
	 * temperature attribute is updated.
	 */
	private class TemperatureListener implements PropertyChangeListener {

		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			// TODO fix this
			StatusView.this.temperature
					.setText(StatusView.decimalFormat.format(StatusView.this.model.getTemperature()));
		}
	}

	/**
	 * An inner class the updates the temperature label whenever the model's
	 * temperature attribute is updated.
	 */
	private class TemperatureAvListener implements PropertyChangeListener {

		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			// TODO fix this
			StatusView.this.temperatureAv
					.setText(StatusView.decimalFormat.format(StatusView.this.model.getAverageTemperature()));
		}
	}

	// -------------------------------------------------------------------------------
	/**
	 * An inner class the updates the status label whenever the model's status
	 * attribute is updated.
	 */
	private class StatusListener implements PropertyChangeListener {

		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			// TODO fix this
			StatusView.this.status.setText(StatusView.this.model.getStatus().toString());
		}
	}

	// -------------------------------------------------------------------------------
	/**
	 * An inner class the updates the days label whenever the model's ticks
	 * attribute is updated.
	 */
	private class TicksListener implements PropertyChangeListener {

		@Override
		public void propertyChange(final PropertyChangeEvent evt) {
			// TODO fix this
			StatusView.this.days.setText(StatusView.decimalFormat.format(StatusView.this.model.getTicks()));
		}
	}

	// -------------------------------------------------------------------------------
	/**
	 * The format string for displaying numeric output.
	 */
	private static final String doubleFormatString = "####.##";
	private static final String intFormatString = "###";
	/**
	 * The formatters for displaying numeric output.
	 */
	private static final DecimalFormat decimalFormat = new DecimalFormat(doubleFormatString);
	private static final DecimalFormat intFormat = new DecimalFormat(intFormatString);
	/**
	 * The rod insertion length value field - cannot be edited by the user.
	 */
	private final JLabel rodLength = new JLabel("");
	/**
	 * The power output value field - cannot be edited by the user.
	 */
	private final JLabel powerOutput = new JLabel("");
	/**
	 * The average power value field - cannot be edited by the user.
	 */
	private final JLabel powerAv = new JLabel("");
	/**
	 * The current temperature value field - cannot be edited by the user.
	 */
	private final JLabel temperature = new JLabel("");
	/**
	 * The average temperature value field - cannot be edited by the user.
	 */
	private final JLabel temperatureAv = new JLabel("");
	/**
	 * The reactor status value field - cannot be edited by the user.
	 */
	private final JLabel status = new JLabel("");
	/**
	 * The reactor days value field - cannot be edited by the user.
	 */
	private final JLabel days = new JLabel("");
	/**
	 * The finish button to end the simulation.
	 */
	private final JButton finish = new JButton("Finish Operation");
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
	public StatusView(final Reactor model) {
		this.model = model;
		this.layoutView();
		this.registerListeners();
		// Initialize the view labels.
		this.rodLength.setText(decimalFormat.format(this.model.getRodsHeight()));
		this.powerOutput.setText(decimalFormat.format(this.model.getPower()));
		this.powerAv.setText(decimalFormat.format(this.model.getAveragePower()));
		this.temperature.setText(decimalFormat.format(this.model.getTemperature()));
		this.temperatureAv.setText(decimalFormat.format(this.model.getAverageTemperature()));
		this.status.setText(this.model.getStatus().toString());
		this.days.setText(decimalFormat.format(this.model.getTicks()));
	}

	// ---------------------------------------------------------------
	/**
	 * Uses the GridLayout to place the labels and buttons.
	 */
	private void layoutView() {
		this.setLayout(new GridLayout(8, 2));

		this.add(new JLabel("Rod Insertion Length"));
		((JComponent) this.add(this.rodLength)).setBorder(new EmptyBorder(10, 10, 10, 10));

		this.add(new JLabel("Current Temperature"));
		((JComponent) this.add(this.temperature)).setBorder(new EmptyBorder(10, 10, 10, 10));

		this.add(new JLabel("Average Temperature"));
		((JComponent) this.add(this.temperatureAv)).setBorder(new EmptyBorder(10, 10, 10, 10));

		this.add(new JLabel("Current Power"));
		((JComponent) this.add(this.powerOutput)).setBorder(new EmptyBorder(10, 10, 10, 10));

		this.add(new JLabel("Average Power"));
		((JComponent) this.add(this.powerAv)).setBorder(new EmptyBorder(10, 10, 10, 10));

		this.add(new JLabel("Status"));
		((JComponent) this.add(this.status)).setBorder(new EmptyBorder(10, 10, 10, 10));

		this.add(new JLabel("Days Elapsed"));
		((JComponent) this.add(this.days)).setBorder(new EmptyBorder(10, 10, 10, 10));

		this.add(new JLabel());
		this.add(this.finish);

	}

	// ---------------------------------------------------------------
	/**
	 * Assigns listeners to the view widgets and the model.
	 */
	private void registerListeners() {
		// Add widget listeners.
		this.finish.addActionListener(new FinishButtonListener());
		// Add model listeners.
		this.model.addPropertyChangeListener(new RodListener());
		this.model.addPropertyChangeListener(new PowerListener());
		this.model.addPropertyChangeListener(new PowerAverageListener());
		this.model.addPropertyChangeListener(new TemperatureListener());
		this.model.addPropertyChangeListener(new TemperatureAvListener());
		this.model.addPropertyChangeListener(new StatusListener());
		this.model.addPropertyChangeListener(new TicksListener());
	}

	// ---------------------------------------------------------------

}