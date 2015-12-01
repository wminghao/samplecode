 '110'
 '101'
'1011'


     assert( '11110' = getString('1111', '11110000001000000100000010000001000000100000010000001000000100000010000001000000100000010000001000000'));
assert( '11110' = getString('1111', '1111'));
assert( '10' = getString('1', '1'));
assert( '1000000001' = getString('1', '100000000'));
assert( '11' = getString('1', '10'));
assert( null = getString(null, "10"));
assert( null = getString("10", null));
assert( null = getString("10", ""));
assert( null = getString("", "10"));
assert( null = getString("12", "abc"));
assert( null = getString("abc", "12"));

//
String getString(String str1, String str2) {
  
    if( str1 == null || str2 == null || str1.length == 0 || str2.length == 0) {
	return null;
    }
  
    if( !isAllbinary(str1) || !isAllBinary(str2)) {
	return null;
    }
  
    int len1 = str1.length;
    int len2 = str2.length;
  
    String result = "";
  
    int i = len1-1;
    int j = len2-1;
  
    int carry = 0;
  
    while( !( i<0 && j<0 ) ) {
    
	int digit1 = i>=0?(str1.charAt(i)-'0'):0;
	int digit2 = j>=0?(str2.charAt(j)-'0'):0;
    
	int add = digit1+digit2+carry;
    
	char res;
    
	res = (add==0||add==2)? '0':'1';
	carry = (add>1)?1:0;
    
	result = String(res) + result;
	i--;
	j--;
    }
  
    if( carry == 1 ) {
	result = String('1') + result;
    }
  
    return result;
}
