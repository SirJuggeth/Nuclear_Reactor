# Nuclear_Reactor

To run:
&nbsp;&nbsp;From project folder, execute these commands in Windows cmd terminal:

  :: Compile all .java files
  > for /R src %f in (*.java) do javac -d bin "%f"

  :: Run the main class
  > java -cp bin cp213.Main

Objective:  
  Try to keep the reactor stable for as long as you can (number of days).

Setup:  
  Start by choosing initial reactor core temperature (between 0-1000) and cooling rod insertion length (between 0-200).   
  You can choose auto-run, and watch the AI make adjustments, or to try to keep it stable manually.

  Power is produced between temperatures of 25-100.  
  Plant shutsdown if rods are inserted to 200 or if temps are below 25.  
  Plant meltsdown if temperature reaches 1000.

  Safe starting settings:  
    Initial temperature: 30  
    Initial rod insertion length: 0
