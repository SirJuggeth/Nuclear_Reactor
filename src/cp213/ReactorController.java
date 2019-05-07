package cp213;

/**
 * A class to control a Reactor model automatically. It's job is to initialize a
 * Reactor and maximize its power output while avoiding a meltdown.
 *
 * @author Justin Harrott
 * @version 2017-11-17
 *
 */
public class ReactorController implements Runnable {

	// The reactor to control.
	private Reactor model = null;

	private double nextTempPrediction = 0.0;
	int rods = 0;

	/**
	 * Constructor.
	 *
	 * @param model
	 *            The reactor to control.
	 */
	public ReactorController(Reactor model) {
		this.model = model;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Runnable#run()
	 *
	 * Run the reactor control.
	 */
	@Override
	public void run() {

		try {

			while (this.model.getStatus() == Reactor.Status.OPERATING && !Thread.interrupted()) {
				// System.out.println(model.getTemperature());
				// System.out.println(model.getAverageTemperature());
				// System.out.println(model.getPower());
				// System.out.println(model.getAveragePower());
				// System.out.println(this.model.getStatus().toString());
				// System.out.println(this.model.getRodsHeight());
				// System.out.println();

				this.model.tick();

				rods = this.model.getRodsHeight();
				nextTempPrediction = (this.model.getTemperature() * Reactor.TEMP_FACTOR - rods + 3);

				if (nextTempPrediction - 1 >= Reactor.MAX_TEMP) {

					// System.out.println("heher");
					this.model.dropRods();
				} else if (safetyCheck(nextTempPrediction + 1)) {

					this.model.raiseRods();
				} else if (safetyCheck(nextTempPrediction)) {
					// do nothing
				} else {
					this.model.lowerRods();
				}

				// this.pcs.firePropertyChange(null, null, null);
				Thread.sleep(300);

			}
		} catch (InterruptedException e) {

		}

		// System.out.println(model.getTemperature());
		// System.out.println(model.getAverageTemperature());
		// System.out.println(model.getPower());
		// System.out.println(model.getAveragePower());
		// System.out.println(this.model.getStatus().toString());
		// System.out.println(this.model.getRodsHeight());
		// System.out.println();

	}

	private boolean safetyCheck(double temp) {
		boolean safe = false;

		while (nextTempPrediction > this.model.getTemperature() && nextTempPrediction < Reactor.MAX_TEMP
				&& rods < Reactor.ROD_LENGTH) {

			rods++;
			nextTempPrediction = (nextTempPrediction * Reactor.TEMP_FACTOR - rods + 3);
		}

		if (nextTempPrediction < Reactor.MAX_TEMP && rods < Reactor.ROD_LENGTH) {
			safe = true;
		}

		return safe;
	}
}
