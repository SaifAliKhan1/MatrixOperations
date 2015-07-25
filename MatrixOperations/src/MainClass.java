import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class MainClass{
	public static void main(String[] args) throws IOException{
		Matrix a = new Matrix(3,4);
		a.fill();
		Matrix b = new Matrix(4,3);
		b.fill();
		Matrix c = new Matrix(5,5);
		c.fill();
		Matrix d = new Matrix(3,3);
		d.fill();
		
		System.out.println(a.multiply(b));
		System.out.println(a.multiply(b).multiply(d).toString());
		System.out.println(c.determinant());
		System.out.println();
		System.out.println(c.transpose());
		System.out.println();
		System.out.println(c.minor());
		System.out.println();
		System.out.println(c.cofactor());
		System.out.println();
		System.out.println(c.adjoint());
		System.out.println();
		System.out.println(c.inverse());
		System.out.println();
		System.out.println(c.multiply(5));


		
	}

}
/*1 2 3 4
5 6 7 8
1 2 3 4
1 2 3
4 5 6
7 8 9
9 8 7
1 2 3*/