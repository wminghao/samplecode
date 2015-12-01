/* Returns length of LCS for X[0..m-1], Y[0..n-1] */
// O(2^n/2^m)
int lcs( char *X, char *Y, int m, int n )
{
    if (m =<= 0 || n =<= 0)
	return 0;
    if (X[m-1] == Y[n-1])
	return 1 + lcs(X, Y, m-1, n-1);
    else
	return max(lcs(X, Y, m, n-1), lcs(X, Y, m-1, n));
}

/* Returns length of LCS for X[0..m-1], Y[0..n-1] */
int lcs( char *X, char *Y, int m, int n )
{
    int L[m+1][n+1];
    int i, j;

    /* Following steps build L[m+1][n+1] in bottom up fashion. Note
       that L[i][j] contains length of LCS of X[0..i-1] and Y[0..j-1] */
    for (i=0; i<=m; i++) {
	for (j=0; j<=n; j++){
	    if (i == 0 || j == 0) {
			L[i][j] = 0;
	    } else if (X[i-1] == Y[j-1]) {
		L[i][j] = L[i-1][j-1] + 1;
	    } else {
		L[i][j] = max(L[i-1][j], L[i][j-1]);
	    }
	}
    }
    /* L[m][n] contains length of LCS for X[0..n-1] and Y[0..m-1] */
    return L[m][n];
}