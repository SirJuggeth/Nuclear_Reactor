package cp213;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 * A simple nuclear reactor simulation. Given a starting temperature and control
 * rods heights, attempt to control the reactor over a period of time.
 *
 * @author Justin Harrott
 * @version 2017-11-17
 *
 */
public class Reactor implements Runnable {

	// ---------------------------------------------------------------
	/**
	 * Enumerated type for the Reactor status. The reactor is assumed to be in
	 * OPERATING mode at the beginning of the simulation.
	 */
	public enum Status {
		FINISHED("Finished"), MELTDOWN("MELTDOWN!!"), OPERATING("Operating"), SHUTDOWN("Shutdown");

		private String statusString;

		Status(final String statusString) {
			this.statusString = statusString;
		}

		@Override
		public String toString() {
			return this.statusString;
		}
	}

	// ---------------------------------------------------------------
	// Public Constants.
	// °C - Room temperature.
	public static final double MIN_TEMP = 25;
	// °C - Meltdown if exceeded.
	public static final double MAX_TEMP = 1000;
	// Minimum temperature at which power is generated.
	public static final double MIN_POWER_TEMP = 100;
	// Maximum power in Mw output at maximum temperature.
	public static final double MAX_POWER = 800;
	// Lengths of rods in cm.
	public static final int ROD_LENGTH = 200;
	// Temperature multiplier per tick.
	public static final double TEMP_FACTOR = 1.125;
	// Range of temperature decrease/increase.
	public static final int RAND_HIGH = 3;
	public static final int RAND_LOW = -3;

	// TODO make sure these are allowed to be public
	/**
	 * Used to signal a change in the base value property of the model.
	 */
	public static final String ROD_HEIGHT_CHANGE = "Base Changed";
	/**
	 * Used to signal a change in the height value property of the model.
	 */
	public static final String ROD_DROP_CHANGE = "Height Changed";

	// Model attributes
	private double temperature = 0.0;
	private double temperatureTotal = 0.0;
	private double tempAverage;
	private int rodHeight = 200;
	private Status status = Reactor.Status.OPERATING;
	private int ticks = 0;
	private double powerOutput = 0.0;
	private double powerAverage = 0.0;
	private double powerTotal = 0.0;
	private boolean ticked = true;

	// ---------------------------------------------------------------
	/**
	 * Allows views to listen to generic changes in the model.
	 */
	private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	/**
	 * Reactor constructor.
	 *
	 * @param initialTemperature
	 *            The initial temperature of the reactor.
	 * @param initialRodsHeight
	 *            The initial heights of the reactor control rods.
	 */
	public Reactor(final double initialTemperature, final int initialRodsHeight) {

		this.temperature = initialTemperature;
		this.rodHeight = initialRodsHeight;

		if (this.temperature >= 1000) {
			this.status = Reactor.Status.MELTDOWN;
		}

		if (this.rodHeight >= 200 || this.temperature <= 25) {
			this.status = Reactor.Status.SHUTDOWN;
		}

		return;
	}

	// ---------------------------------------------------------------
	/**
	 * Attaches listeners to the model.
	 *
	 * @param listener
	 *            The listener to attach to the model.
	 */
	public void addPropertyChangeListener(final PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(listener);
	}

	// ---------------------------------------------------------------
	/**
	 * Attaches listeners to the model for a particular property.
	 *
	 * @param propertyName
	 *            The name of the property to listen for.
	 * @param listener
	 *            The listener to attach to the model.
	 */
	public void addPropertyChangeListener(final String propertyName, final PropertyChangeListener listener) {
		this.pcs.addPropertyChangeListener(propertyName, listener);
	}

	/**
	 * Drops the rods entirely into the reactor core - i.e. set the rods lengths to
	 * the maximum rods lengths.
	 */
	public void dropRods() {

		this.rodHeight = Reactor.ROD_LENGTH;
		this.status = Reactor.Status.SHUTDOWN;
		this.pcs.firePropertyChange(null, null, null);

	}

	/**
	 * Returns the average power produced by the reactor since the start of a
	 * simulation.
	 *
	 * @return average power.
	 */
	public double getAveragePower() {
		// TODO make this work
		return this.powerAverage;

	}

	/**
	 * Returns the average temperature of the reactor since the start of a
	 * simulation.
	 *
	 * @return average temperature.
	 */
	public double getAverageTemperature() {

		return this.tempAverage;

	}

	/**
	 * Returns the reactor's current power level.
	 *
	 * @return power.
	 */
	public double getPower() {

		return this.powerOutput;

	}

	/**
	 * Returns the reactor's current rod height *(rod height, rod length, and rod
	 * insertion length are all the same thing ... not my choice, I'm just having to
	 * roll with it)*.
	 *
	 * @return rodsHeight.
	 */
	public int getRodsHeight() {

		return this.rodHeight;

	}

	/**
	 * Returns the reactor's current status.
	 *
	 * @return status.
	 */
	public Status getStatus() {

		return this.status;

	}

	/**
	 * Returns the reactor's current temperature.
	 *
	 * @return temperature.
	 */
	public double getTemperature() {

		return this.temperature;

	}

	/**
	 * Returns the number of ticks since the beginning of a simulation.
	 *
	 * @return ticks
	 */
	public int getTicks() {

		return this.ticks;

	}

	/**
	 * Lower the rod heights by one step. Rods cannot be lowered by more than one
	 * step per tick.
	 */
	public void lowerRods() {

		if (ticked) {
			this.rodHeight += 1;
			this.ticked = false;
		}

		return;
	}

	/**
	 * Raise the rod heights by one step. Rods cannot be raised by more than one
	 * step per tick.
	 */
	public void raiseRods() {

		if (ticked) {
			this.rodHeight -= 1;
			this.ticked = false;
		}

		return;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Runnable#run()
	 *
	 * Run the reactor simulation.
	 */
	@Override
	public void run() {
		// TODO clean this up get it working

		try {

			while (this.status == Status.OPERATING && !Thread.interrupted()) {

				tick();
				Thread.sleep(1000);

			}
			this.pcs.firePropertyChange(null, null, null);
		} catch (InterruptedException e) {

		}
		this.pcs.firePropertyChange(null, null, null);
	}

	/**
	 * Increment the simulation tick by one. Update the reactor temperature, power,
	 * and status (in that order), and allow the rods to be raised or lowered during
	 * this tick.
	 */
	public void tick() {

		// once the simulation starts, the ticks don't stop
		this.pcs.firePropertyChange(null, null, null);
		this.ticks++;
		this.temperatureChange();
		this.powerChange();
		this.setStatus();
		this.ticked = true;

		this.pcs.firePropertyChange(null, null, null);

	}

	/**
	 * Sets reactor status to FINISHED.
	 */
	public void quit() {
		this.status = Status.FINISHED;
		return;
	}

	/**
	 * calculates new temperature
	 * 
	 * @param temp
	 *            the current temperature
	 */
	private void temperatureChange() {

		this.temperature = (temperature * TEMP_FACTOR - this.rodHeight + ((Math.random() * 6) - 3));

		this.temperatureTotal += this.temperature;
		this.tempAverage = this.temperatureTotal / this.ticks;

		this.pcs.firePropertyChange(null, null, null);
		return;
	}

	/**
	 * calculates new power and sets power output
	 * 
	 * @param pow
	 *            the current power
	 */
	private void powerChange() {

		if (this.temperature > MIN_POWER_TEMP) {
			this.powerOutput = (((this.temperature - MIN_POWER_TEMP) * MAX_POWER) / (MAX_TEMP - MIN_POWER_TEMP));
		} else {
			this.powerOutput = 0;
		}
		this.powerTotal += this.powerOutput;
		this.powerAverage = this.powerTotal / this.ticks;

		this.pcs.firePropertyChange(null, null, null);
		return;
	}

	/**
	 * Sets Reactor status based on current temperature.
	 */
	private void setStatus() {

		if (this.temperature >= MAX_TEMP) {
			this.status = Reactor.Status.MELTDOWN;
		}

		else if (this.temperature <= MIN_TEMP) {
			this.status = Reactor.Status.SHUTDOWN;
		}

		else if (this.rodHeight >= ROD_LENGTH) {
			this.status = Reactor.Status.SHUTDOWN;
		}
		this.pcs.firePropertyChange(null, null, null);
		return;
	}

}
