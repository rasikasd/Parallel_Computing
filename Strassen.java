import java.util.*;
import java.math.*;
class Strassen
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		//System.out.println("Enter the size of matrix to be formed");
		int n = sc.nextInt();
		int i = 0; int j = 0; int k = 0;
		for(k=0;k<1;k++)
	{

		//n = 4;
		//System.out.println(n);
		int minimum = 1;
		int maximum = 50;
		float [][]a = new float[n][n];
		float [][]b = new float[n][n];
		float [][]c = new float[n][n];
		//float [][]d = new float[n][n];
		for(i=0;i<n;i++)
		{
			for(j=0;j<n;j++)
			{
				//a[i][j] = (float)(minimum + (int)(Math.random() * maximum));
                //b[i][j] = (float)(minimum + (int)(Math.random() * maximum));
				a[i][j] = sc.nextInt();

			}

			for(j=0;j<n;j++)
			{
				//a[i][j] = (float)(minimum + (int)(Math.random() * maximum));
                //b[i][j] = (float)(minimum + (int)(Math.random() * maximum));
				b[i][j] = sc.nextInt();
			}
		}
		double start = System.nanoTime();
		c = strassen(a,b);
		double end = System.nanoTime();

		/*for(i=0;i<n;i++)
		{
			for(j=0;j<n;j++)
			{
				System.out.print(c[i][j]+" ");
			}
			System.out.println();
		} */

		System.out.println("Time taken for n = "+n+" is : "+(end-start)/1000000000 + " Seconds");

	}
	}

public static float[][] strassen(float[][]a, float[][]b)
	{
		int n = a.length;
		float[][] R = new float[n][n];
		if(n==1)
			R[0][0] = a[0][0]*b[0][0];
		else
		{
			float[][] a11 = new float[n/2][n/2];
			float[][] a12 = new float[n/2][n/2];
			float[][] a21 = new float[n/2][n/2];
			float[][] a22 = new float[n/2][n/2];
			float[][] b11 = new float[n/2][n/2];
			float[][] b12 = new float[n/2][n/2];
			float[][] b21 = new float[n/2][n/2];
			float[][] b22 = new float[n/2][n/2];

			split(a,a11,0,0);
			split(a,a12,0,n/2);
			split(a,a21,n/2,0);
			split(a,a22,n/2,n/2);
			split(b,b11,0,0);
			split(b,b12,0,n/2);
			split(b,b21,n/2,0);
			split(b,b22,n/2,n/2);

			float [][] M1 = strassen(add(a11, a22), add(b11, b22));
           		float [][] M2 = strassen(add(a21, a22), b11);
            		float [][] M3 = strassen(a11, sub(b12, b22));
            		float [][] M4 = strassen(a22, sub(b21, b11));
            		float [][] M5 = strassen(add(a11, a12), b22);
            		float [][] M6 = strassen(sub(a21, a11), add(b11, b12));
            		float [][] M7 = strassen(sub(a12, a22), add(b21, b22));

			float [][] C11 = add(sub(add(M1, M4), M5), M7);
            		float [][] C12 = add(M3, M5);
            		float [][] C21 = add(M2, M4);
            		float [][] C22 = add(sub(add(M1, M3), M2), M6);

			join(C11, R, 0 , 0);
            		join(C12, R, 0 , n/2);
            		join(C21, R, n/2, 0);
            		join(C22, R, n/2, n/2);


		}
		return R;
	}

public static void split(float[][]P,float[][]C,int iB,int jB)
	{
		int i2 = iB;
		for(int i1=0;i1< C.length;i1++)
		{
			int j2 = jB;
			for(int j1=0; j1<C.length;j1++)
			{
				C[i1][j1] = P[i2][j2];
				j2++;
			}
			i2++;
		}
	}

public static float[][] add(float[][] a,float[][] b)
	{
		int n = a.length;
		float[][] c = new float[n][n];
		for(int i = 0;i<n;i++)
		{
			for(int j=0;j<n;j++)
				c[i][j] = a[i][j] + b[i][j];
		}
	return c;
	}

public static float[][] sub(float[][] a,float[][] b)
	{
		int n = a.length;
		float[][] c = new float[n][n];
		for(int i = 0;i<n;i++)
		{
			for(int j=0;j<n;j++)
				c[i][j] = a[i][j] - b[i][j];
		}
	return c;
	}
public static void join(float[][]P,float[][]C,int iB,int jB)
	{
		int i2 = iB;
		for(int i1=0;i1< P.length;i1++)
		{
			int j2 = jB;
			for(int j1=0; j1< P.length;j1++)
			{
				C[i2][j2] = P[i1][j1];
				j2++;
			}
			i2++;
		}
	}
}
