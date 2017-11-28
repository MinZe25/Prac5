package tempAnalyzer;

import java.awt.Color;
import java.io.*;

import jconsole.JConsole;

public class TempAnalyzer {

	public static void main(String[] args) {
		
		JConsole console = new JConsole (80,30);

		String[] months = { "JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER",
				"OCTOBER", "NOVEMBER", "DECEMBER" };
		String[] cities = { "BARCELONA", "HANOI", "NOVOSIBIRSK", "USUHAIA" };
		String[] menuOptions = { "Year max. and min. (with months)", "Average temp of a month",
				"Year in which month was the warmest", "Months with temp. above threshold", "change city",
				"Quit program" };
		int city;
		int option;
		int year, month;
		int monthMinimum, monthMaximum;
		int howMany;
		double average;
		double threshold;
		double[][] temperatures;
		boolean[] selectedMonths = new boolean[12];
		
		console.println("TEMPERATURE ANALYSIS");
        console.println("----------- --------\n");

        city = menu("please choose a city ", cities, console);
        temperatures = GetCityTemps(city);
        console.println("Selected city: "+ cities[city - 1]);
        pressAnyKeyToContinue(console);
        console.clear();

        option = menu("please, choose operation", menuOptions, console);
        while (option != 6)
        {
            switch (option)
            {

                case 1:
                    year = readInteger("Please enter year?", 2000, 2016, console);
                    monthMinimum = searchMin(temperatures, year-2000); 
                    monthMaximum = searchMax(temperatures, year-2000);
                    console.setForegroundColor(Color.YELLOW);
                    console.println("\nIn the city of "+cities[city - 1]+" the year "+ year);
                    console.println("  Minimum temp. was recorded on "+months[monthMinimum]+" and it was "+temperatures[year - 2000][monthMinimum]+" degrees");
                    console.println("  Maximum temp. was recorded on "+months[monthMaximum]+" and it was "+temperatures[year - 2000][monthMaximum]+" degrees");
                    console.resetColor();
                    break;

                case 2:
                    month = readInteger("Please enter month (1 January - 12 december)? ", 1, 12, console);
                    average = calculateMonthlyAverage(temperatures, month);
                    console.setForegroundColor(Color.YELLOW);
                    console.println("\nIn the city of  "+ cities[city - 1]);
                    console.println(" The average temp. in "+months[month - 1]+" is "+average+" degrees");
                    console.resetColor();
                    break;

                case 3:
                    month = readInteger("Please enter month (1 January - 12 december)?", 1, 12, console);
                    year = WarmestYear(temperatures, month);
                    console.setForegroundColor(Color.YELLOW);
                    console.println("\nIn the city of  "+ cities[city - 1]);
                    console.println(" The warmest "+months[month - 1]+" was in "+year+" when the temperature reached "+temperatures[year - 2000][month - 1]+" degrees");
                    console.resetColor();
                    break;

                case 4:
                    year = readInteger("Please enter year", 2000, 2016,console);
                    console.print("Please enter threshold? ");
                    console.setForegroundColor(Color.GREEN);
                    threshold = console.readDouble();
                    
                    /* COMPLETE */
                    
                    break;

                case 5:
                    city = menu("Please choose a city", cities, console);
                    temperatures = GetCityTemps(city);
                    console.println("Selected city: "+ cities[city - 1]);
                    break;
            }

            pressAnyKeyToContinue(console);
            console.clear();
            option = menu("Please choose an option", menuOptions, console);
        }
        
        System.exit(0);

	} // main ends here.
	
	// ----------- PROCs -----------------------
	
	 static int menu(String introduction, String[] options, JConsole console)
     {
         /* COMPLETE */
     }
	 
	 
	 
	 static int searchMin(double[][] t, int row)
     {
         /* COMPLETE */
     }
	 
	 static int searchMax(double[][] t, int row)
     {
		 /* COMPLETE */
     }
	 
	 
	 static double calculateMonthlyAverage(double[][] t, int month)
     {
		 /* COMPLETE */
     }
	 
	 static int WarmestYear(double[][] t, int month)
     {
		 /* COMPLETE */
     }
	 
	 static void MonthsAbove(double[][] t, int year, double threshold, boolean[] m)
     {
		 /* COMPLETE */
     }
	 
	 static int readInteger(String msg, int min, int max, JConsole console)
     {
         int answer;
         console.print(msg + " ");
         console.setForegroundColor(Color.GREEN);
         answer = console.readInt();
         while (answer < min || answer > max)
         {
        	 console.setForegroundColor(Color.RED);
             console.println("  incorrect value. Must be between "+ min +" and "+ max);
             console.resetColor();
             console.print(msg + " ");
             console.setForegroundColor(Color.GREEN);
             answer = console.readInt();
         }
         console.resetColor();
         return answer;
     }
	 
	 
	 static void pressAnyKeyToContinue(JConsole console)
     {
         console.setCursorPosition(0, console.getRows()-1);
         console.print("Pres any key to continue");
         console.readKey(true);
     }
	 
	 static double[][] GetCityTemps(int city)
     {
         if (city < 1 || city > 4)
         {
             throw new IllegalArgumentException("Parameter city must be in [1,4]");
         }

         String line;
         String[] numbers;
         double[][] result;
         String[] filename = {"DATA/Barcelona.txt",
             "DATA/Hanoi.txt",
             "DATA/Novosibirsk.txt",
             "DATA/Ushuaia.txt"};
         
         BufferedReader bur = null;
         result = new double[17][12];
         
         try {
        	 bur = new BufferedReader(new FileReader(filename[city-1]));
        	 for (int year = 0; year <= 16; year++) {
        		 line = bur.readLine();
        		 numbers = line.split(", ");
        		 for (int month=0; month<=11; month++) {
        			 result[year][month] = Double.parseDouble(numbers[month]);
        		 }
        	 }
         }
         catch (Exception e) {
        	 javax.swing.JOptionPane.showMessageDialog(null, "Exception processing file "+filename[city-1]+"\n"+e.getClass().getSimpleName()+"\n"+e.getMessage()+"\nProgram will terminate", "File not found", javax.swing.JOptionPane.ERROR_MESSAGE );
        	 System.exit(1);
         }
         finally {
        	 try {bur.close();} catch(Exception e){}
         }

         return result;
     }

}
