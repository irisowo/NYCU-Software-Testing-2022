     1	// Jeff Offutt - Junie 1989, Java version 2003
     2	
     3	// Stutter checks for repeat words in a text file.
     4	// It prints a list of repeat words, by line number.
     5	// Stutter will accept standard input or a list
     6	// of file names.
     7	
     8	import java.io.*;
     9	
    10	class Stutter
    11	{
    12	  // Class variables used in multiple methods.
    13	  private static boolean lastdelimit = true;
    14	  private static String curWord = "", prevWord = "";
    15	  private static char delimits [] =
    16	          {'	', ' ', ',', '.', '!', '-', '+', '=', ';', ':', '?', '&', '{', '}', '\\'};
    17	
    18	//************************************************
    19	// main parses the arguments, decides if stdin
    20	// or a file name, and calls Stut().
    21	//************************************************
    22	public static void main (String[] args) throws IOException
    23	{
    24	   String fileName;
    25	   FileReader myFile;
    26	   BufferedReader inFile = null;
    27	
    28	   if (args.length == 0)
    29	   {  // no file, use stdin
    30	      inFile = new BufferedReader (new InputStreamReader (System.in));
    31	   }
    32	   else
    33	   {
    34	      fileName = args [0];
    35	      if (fileName == null)
    36	      {  // no file name, use stdin
    37	         inFile = new BufferedReader (new InputStreamReader (System.in));
    38	      }
    39	      else
    40	      {  // file name, open the file.
    41	         myFile = new FileReader (fileName);
    42	         inFile = new BufferedReader (myFile);
    43	      }
    44	   }
    45	
    46	   stut (inFile);
    47	}
    48	
    49	//************************************************
    50	//************************************************
    51	private static void stut (BufferedReader inFile) throws IOException
    52	{
    53	   String inLine;
    54	   char c;
    55	   int linecnt = 1;
    56	   while ((inLine = inFile.readLine()) != null)
    57	   {  // For each line
    58	
    59	      for (int i=0; i<inLine.length(); i++)
    60	      {  // for each character
    61	         c = inLine.charAt(i);
    62	
    63	         if (isDelimit (c))
    64	         {  // Found an end of a word.
    65	            checkDupes (linecnt);
    66	         }
    67	         else
    68	         {
    69	           lastdelimit = false;
    70	           curWord = curWord + c;
    71	         }
    72	      }
    73	      checkDupes (linecnt);
    74	      linecnt++;
    75	
    76	   }
    77	}  // end Stut
    78	
    79	//************************************************
    80	//************************************************
    81	private static void checkDupes (int line)
    82	{
    83	   if (lastdelimit)
    84	   return; // already checked, keep skipping
    85	   lastdelimit = true;
    86	   if (curWord.equals(prevWord))
    87	   {
    88	      System.out.println ("Repeated word on line " + line + ": " + prevWord+ " " + curWord);
    89	   }
    90	   else
    91	   {
    92	      prevWord = curWord;
    93	   }
    94	   curWord = "";
    95	}  // end checkDupes
    96	
    97	//************************************************
    98	//************************************************
    99	private static boolean isDelimit (char C)
   100	{
   101	   for (int i = 0; i < delimits.length; i++)
   102	      if (C == delimits [i])
   103	         return (true);
   104	   return (false);
   105	}
   106	
   107	} // end class Stutter