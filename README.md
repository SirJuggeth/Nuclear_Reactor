# Nuclear_Reactor

To run:  
&nbsp;&nbsp;From project folder, execute these commands in Windows cmd terminal:

  &nbsp;&nbsp;:: Compile all .java files
  &nbsp;&nbsp;> for /R src %f in (*.java) do javac -d bin "%f"

  &nbsp;&nbsp;:: Run the main class
  &nbsp;&nbsp;> java -cp bin cp213.Main

Objective:  
  &nbsp;&nbsp;Try to keep the reactor stable for as long as you can (number of days).

Setup:  
  &nbsp;&nbsp;Start by choosing initial reactor core temperature (between 0-1000) and cooling rod insertion length (between 0-200).   
  &nbsp;&nbsp;You can choose auto-run and watch the AI make adjustments, or to try to keep it stable manually.

  &nbsp;&nbsp;Power is produced between temperatures of 25-100.  
  &nbsp;&nbsp;Plant shutsdown if rods are inserted to 200 or if temps are below 25.  
  &nbsp;&nbsp;Plant meltsdown if temperature reaches 1000.

  &nbsp;&nbsp;Safe starting settings:  
    &nbsp;&nbsp;&nbsp;&nbsp;Initial temperature: 30  
    &nbsp;&nbsp;&nbsp;&nbsp;Initial rod insertion length: 0
