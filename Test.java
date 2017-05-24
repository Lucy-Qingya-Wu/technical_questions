import java.util.*;

public class Test {

	public static boolean isUnique(String s){
		// change char to number and the corrosponding location in the char array +1

		// if there is a number in the location already, return false

		int length = s.length();
		boolean count[] = new boolean[256];
		Arrays.fill(count, false);


		int i = 0;
		while (i < length){
			int num = Character.toLowerCase(s.charAt(i)) - 'a';

			if (count[num]){
				return false;
			}else{
				count[num] = true;
			}
			i += 1;
		}
		return true;


		// in the end of the loop, return true
	}

	public static boolean isPermutation(String s1, String s2){

		if (s1.length() != s2.length()){
			return false;
		}
		// s1 and s2 only contain ascii char
		int count[] = new int[256];
		Arrays.fill(count, 0);

		// going through s1 and set corrsponding position +1
		for (int i = 0; i < s1.length(); i ++){
			int num = s1.charAt(i);
			count[num] += 1;
		}

		// going through s2 and set corrsponding position -1, if the value become negative,
		// then return fasle
		for (int i = 0; i < s2.length(); i++){
			int num = s2.charAt(i);
			count[num] -= 1;

			if (count[num] < 0){
				return false;
			}
		}


		// at the end of the two loop, return true
		return true;

	}


	// "Mr John Smith    "
	// "Mr%20John%20Smith"
	public static char[] URLify(char[] s, int num){

		// count how many space in s
		int len = 0;
	    int c = 0;

		while (len < num){
			if (s[len] == ' '){
				c += 1;
			}
			len += 1;
		}

		// keep moving char to the end
		int i = num - 1;
		int j = num + 2 * c - 1;

		while (i >= 0){
			if (s[i] == ' '){
				s[j] = '0';
				s[j-1] = '2';
				s[j-2] = '%';
				j -= 3;
			}else{
				s[j] = s[i];
				j -= 1;
			}

			i -= 1;
		}

		return s;

	}

	public static boolean palindromePermutation(String s){

		int[] int_array = new int[26];
		int count = 0;

		// count how many times each character show up in s
		for (int i = 0; i < s.length(); i++){
			char c = s.charAt(i);
			if (c != ' '){
				int num = Character.toLowerCase(c) - 'a';
				int_array[num] += 1;
				if (int_array[num] % 2 == 0){
					count--;
				}else{
					count++;
				}
			}

		}

		return count <= 1;
	}

	public static boolean oneWay(String s1, String s2){

	    int len1 = s1.length();
	    int len2 = s2.length();

	    if (len1 == len2){
	    	return replace(s1, s2);
	    }else if(len1 + 1 == len2){
	    	return insert_or_delete(s1, s2);
	    }else if(len1 - 1 == len2){
	    	return insert_or_delete(s2, s1);
	    }else{
	    	return false;
	    }
	}


	// s1.length() < s2.length()
	public static boolean insert_or_delete(String s1, String s2){
		int i = 0;
		int j = 0;
		boolean flag = false;
		while (i < s1.length()){
			if (s1.charAt(i) != s2.charAt(j)){
				if (flag){
					return false;
				}else{
					flag = true;
					j += 1;
				}
			}else{
				i += 1;
				j += 1;
			}
		}
		return true;
	}

	// this method will return if s1 and s2 just one letter difference
	public static boolean replace(String s1, String s2){
		boolean flag = false;
		for (int i = 0; i < s1.length(); i++){
			if (s1.charAt(i) != s2.charAt(i)){
				if (flag){
					return false;
				}else{
					flag = true;
				}
			}
		}
		return true;
	}

	public static String stringCompression(String s){

		// figure out whether it is worth to compress the string
		int total = longerThanOriginalOne(s);

	    if (total >= s.length()) return s;


		// if it is worth to compress it, build it
		StringBuilder compressed = new StringBuilder(total);

		int count = 1;
		char c = s.charAt(0);

		for (int i = 1; i < s.length(); i++){

			if (s.charAt(i) == c){
				count += 1;
			}else{
				compressed.append(c);
				compressed.append(Integer.toString(count));

				c = s.charAt(i);
				count = 1;

			}
		}
		compressed.append(c);
		compressed.append(Integer.toString(count));
		return compressed.toString();

	}

	// return true if total >= s.length(), else, false
	public static int longerThanOriginalOne(String s){

		int count = 1;
		char c = s.charAt(0);

		int total = 0;

		for (int i = 1; i < s.length(); i++){
			if (s.charAt(i) == c){
				count += 1;
			}else{
				total += 1 + Integer.toString(count).length();
				c = s.charAt(i);
				count = 1;

			}
		}
		total += 1 + Integer.toString(count).length();
	    return total;


	}


  public static int[][] zero_matrix(int[][] m, int rows, int cols){

      // check left top
      boolean left_top = m[0][0] == 0? true:false;


      // check first row
      boolean first_row = false;
      for (int i= 0; i<cols; i++){
          if (m[0][i] == 0){
              first_row = true;
              break;
          }
      }

      if (first_row){
          System.out.println("first_row");
      }

      // check first col
      boolean first_col = false;
      for (int i= 0; i<rows; i++){
          if (m[i][0] == 0){
              first_col = true;
              break;
          }
      }
      if (first_col){
          System.out.println("first_col");
      }

      for (int row =1; row<rows; row++){
          for (int col=1; col<cols; col++){
              if (m[row][col] == 0){
                  m[0][col] = 0;
                  m[row][0] = 0;
              }
          }
      }

      for (int i = 1; i<rows; i++){
          if (m[i][0] == 0){
              Arrays.fill(m[i], 0);
          }
      }

      for (int j = 1; j<cols; j++){
          if (m[0][j] == 0){
              for (int i = 1; i<rows; i++){
                  m[i][j] = 0;
              }
          }
      }

      if (left_top){
          Arrays.fill(m[0], 0);
          for (int i = 1; i<rows; i++){
              m[i][0] = 0;
          }
      }else{
          if(first_col){
              for (int i = 1; i<rows; i++){
                  m[i][0] = 0;
              }

          }
          if(first_row){
              Arrays.fill(m[0], 0);
          }
      }
      return m;
  }

	public static void main(String [] args){

//		System.out.println("hey there");
//
//		String a = "abcde";
//		System.out.println(isUnique(a));
//
//		System.out.println(isPermutation("abcca", "abccd"));
//
//		char[] s = "Mr John Smith    ".toCharArray();
//		System.out.println(URLify(s, 13));
//		s = "Mr a ha      ".toCharArray();
//		System.out.println(URLify(s, 7));
//
//		System.out.println(palindromePermutation("Tact Coa"));
//		System.out.println(palindromePermutation("Tact boa"));
//		System.out.println(palindromePermutation("Tact bbcoa"));
//		System.out.println(palindromePermutation("T a c tb b ctat"));
//
//		System.out.println(oneWay("pale", "ple"));
//		System.out.println(oneWay("pales", "pale"));
//		System.out.println(oneWay("pale", "bale"));
//		System.out.println(oneWay("pale", "bae"));
//		System.out.println(oneWay("pale   ", "pa   e"));
//		System.out.println(oneWay("pale", "pal e"));


//		System.out.println(stringCompression("aabbccd"));
//		System.out.println(stringCompression("aabbccdddddddd"));

//		StringBuilder s = new StringBuilder();
//		s.append("abc");
//		s.append('z');
//		System.out.println(s.toString());


//      int rows = 4;
//      int cols = 5;

//      int[] row0 = new int[]{1, 2, 3, 2, 3};
//      int[] row1 = new int[]{4, 0, 6, 0, 6};
//      int[] row2 = new int[]{7, 8, 9, 8, 9};
//      int[] row3 = new int[]{7, 8, 0, 8, 0};


//      int[][] m = new int[][]{row0, row1, row2, row3};



//      for(int i=0; i<rows; i++){
//          System.out.println(Arrays.toString(m[i]));
//      }

//      System.out.println("---------------------");

//      m = zero_matrix(m, rows, cols);
//      for(int i=0; i<rows; i++){
//          System.out.println(Arrays.toString(m[i]));
//      }

	}
}
