package cp213;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.swing.JFrame;

/**
 * Methods to run a reactor or controller.
 *
 * @author Justin Harrott
 * @version 2017-11-17
 *
 */
public class Main {

	// initialize common variables

	/**
	 * Reactor model
	 */
	private Reactor model = null;

	private final ExecutorService threadPool = Executors.newSingleThreadExecutor();

	/**
	 * opens <code>StartWindow</code> and asks user for initial temperature and
	 * initial rod length and then asks them to choose whether to run reactor with
	 * manual control or auto run, then runs reactor in selected mode. Also has a
	 * quit button.
	 */
	// TODO make this run

	public static void main(final String args[]) {

		final StartWindow startWin = new StartWindow();
		final JFrame f = new JFrame("Nuclear Reactor - Initialize");
		f.setContentPane(startWin);
		f.setSize(500, 300);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
	}

	// ---------------------------------------------------------------
	/**
	 * Run the Reactor model given an initial temperature and an initial rod
	 * lengths.
	 * 
	 * @param initialTemperature
	 *            Initial reactor temperature.
	 * @param initialRodsHeight
	 *            Initial reactor rod heights.
	 */
	public void RunReactor(final double initialTemperature, final int initialRodsHeight) {

		this.model = new Reactor(initialTemperature, initialRodsHeight);
		threadPool.execute(this.model);
		final ManualControlView view = new ManualControlView(this.model);
		final JFrame f = new JFrame("Nuclear Reactor - On Manual Control");
		f.setContentPane(view);
		f.setSize(500, 300);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

	}

	/**
	 * Run the Reactor model given an initial temperature and an initial rod
	 * lengths.
	 *
	 * @param initialTemperature
	 *            Initial reactor temperature.
	 * @param initialRodsHeight
	 *            Initial reactor rod heights.
	 */
	public void RunReactorController(final double initialTemperature, final int initialRodsHeight) {

		this.model = new Reactor(initialTemperature, initialRodsHeight);
		ReactorController pilot = new ReactorController(this.model);
		final AutoPilotView view = new AutoPilotView(this.model);
		threadPool.execute(pilot);

		final JFrame f = new JFrame("Nuclear Reactor - On Autopilot");
		f.setContentPane(view);
		f.setSize(500, 300);
		f.pack();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);

	}

}
