package cp213;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * initial window that asks user for initial temperature and initial rod length
 * and then asks them to choose whether to run reactor with manual control or
 * auto run.
 * 
 * @author Justin Harrott ID: 141669800
 * 
 * @version 2017 M11 28 16:54:04
 */
@SuppressWarnings("serial")
public class StartWindow extends JPanel {

	// ---------------------------------------------------------------
	/**
	 * An inner class that uses a FocusListener to access the numeric field. It sets
	 * the model values when the field loses focus.
	 */
	private class TemperatureFieldListener implements FocusListener {
		// Automatically highlight the entire contents of the numeric field.
		@Override
		public void focusGained(final FocusEvent evt) {
			StartWindow.this.temperature.selectAll();
		}

		@Override
		public void focusLost(final FocusEvent evt) {

			try {
				initTemp = Double.parseDouble(StartWindow.this.temperature.getText());
			} catch (final java.lang.NumberFormatException e) {
				JOptionPane.showMessageDialog(StartWindow.this.getTopLevelAncestor(), "Please enter a real number.");
			}
		}
	}

	// ---------------------------------------------------------------
	/**
	 * An inner class that uses a FocusListener to access the numeric field. It sets
	 * the model values when the field loses focus.
	 */
	private class HeightFieldListener implements FocusListener {
		// Automatically highlight the entire contents of the numeric field.
		@Override
		public void focusGained(final FocusEvent evt) {
			StartWindow.this.height.selectAll();
		}

		@Override
		public void focusLost(final FocusEvent evt) {

			try {
				initHeight = Integer.parseInt(StartWindow.this.height.getText());
			} catch (final java.lang.NumberFormatException e) {
				JOptionPane.showMessageDialog(StartWindow.this.getTopLevelAncestor(),
						"Please enter a positive integer.");
			}
		}
	}

	// ---------------------------------------------------------------
	/**
	 * An inner class that uses an ActionListener to access the buttons. It sets the
	 * model values when the button is pressed.
	 */
	private class ButtonListener implements ActionListener {

		@Override
		public void actionPerformed(final ActionEvent evt) {

			if (evt.getSource() == quit) {
				System.exit(0);
			} else if (evt.getSource() == autoRun) {
				mainObject.RunReactorController(initTemp, initHeight);
				// TODO make window dissapear after starting reactor simulation.
				// this.setVisible(false);
			} else {
				mainObject.RunReactor(initTemp, initHeight);

			}

		}
	}

	// -------------------------------------------------------------------------------

	// set all attributes and Jframe components

	/**
	 * Initial condition vairables
	 */
	double initTemp = 0.0;
	int initHeight = 0;

	/**
	 * The format string for reading / displaying numeric input / output.
	 */
	private static final String tempFormatString = "####.##";
	private static final String heightFormatString = "###";
	/**
	 * The formatters for reading / displaying numeric input / output.
	 */
	private static final DecimalFormat decimalFormat = new DecimalFormat(tempFormatString);
	private static final DecimalFormat intFormat = new DecimalFormat(heightFormatString);
	/**
	 * The temperature text field for input.
	 */
	private final JTextField temperature = new JTextField(tempFormatString.length());
	/**
	 * The rod height text field for input.
	 */
	private final JTextField height = new JTextField(heightFormatString.length());
	/**
	 * Button that starts <code>AutoPilotView</code> with initial rod height and
	 * temperature.
	 */
	private final JButton autoRun = new JButton("Auto Run");
	/**
	 * Button that starts <code>ManualControlView</code> with initial rod height and
	 * temperature.
	 */
	private final JButton manualControl = new JButton("Manual Control");
	/**
	 * Button that quits application.
	 */
	private final JButton quit = new JButton("Quit");

	/**
	 * Main class object
	 */
	Main mainObject = new Main();

	// ---------------------------------------------------------------
	/**
	 * The view constructor.
	 *
	 * @param model
	 *            The right triangle model to view.
	 */
	public StartWindow() {

		this.layoutView();
		this.registerListeners();

		// Initialize the view labels.
		this.temperature.setText(decimalFormat.format(0.00));
		this.height.setText(intFormat.format(0));
	}

	// ---------------------------------------------------------------
	/**
	 * Uses the GridLayout to place the labels and buttons.
	 */
	private void layoutView() {
		// Define the widgets.
		this.temperature.setHorizontalAlignment(SwingConstants.RIGHT);
		this.height.setHorizontalAlignment(SwingConstants.RIGHT);
		// Lay out the panel.
		this.setLayout(new GridLayout(7, 2));
		this.setBorder(new EmptyBorder(10, 10, 10, 10));

		this.add(new JLabel("Set initial temperature to: "));
		// this.add(new JLabel(""));
		this.add(this.temperature);

		this.add(new JLabel("Set initial rod insertion length to: "));
		// this.add(new JLabel(""));
		this.add(this.height);

		this.add(this.autoRun);
		// this.add(new JLabel(""));
		this.add(this.manualControl);

		this.add(new JLabel(""));
		this.add(this.quit);
		// this.add(new JLabel(""));

		this.add(new JLabel("************************************************"));
		this.add(new JLabel("************************************************"));

		this.add(new JLabel("Power prodcution temps: 25-100"));
		this.add(new JLabel("Meltdown temp: 1000"));

		this.add(new JLabel("Shutdown: rod inserted 200; temp < 25"));
		// this.add(new JLabel(""));

	}

	// ---------------------------------------------------------------
	/**
	 * Assigns listeners to the field widgets and the model.
	 */
	private void registerListeners() {
		// Add widget listeners.
		this.temperature.addFocusListener(new TemperatureFieldListener());
		this.height.addFocusListener(new HeightFieldListener());
		this.autoRun.addActionListener(new ButtonListener());
		this.manualControl.addActionListener(new ButtonListener());
		this.quit.addActionListener(new ButtonListener());
	}

	// ---------------------------------------------------------------

}