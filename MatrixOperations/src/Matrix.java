import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Matrix {
	int rows;
	int coloums;
	Double[][] n;

	public Matrix(int r, int c) {
		rows = r;
		coloums = c;
		n = new Double[rows][coloums];
	}

	public void fill() throws IOException {
		// BufferedReader bf = new BufferedReader(new
		// InputStreamReader(System.in));
		// String[] s = bf.readLine().split("\n");
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < rows; i++) {
			String[] s = bf.readLine().split(" ");
			for (int j = 0; j < coloums; j++) {
				n[i][j] = Double.parseDouble(s[j]);
			}
		}
	}
	public Matrix multiply(double a){
		Matrix m = new Matrix(rows,coloums);
		for(int i=0;i<rows;i++){
			for(int j=0;j<coloums;j++){
				m.n[i][j]= a*n[i][j];
			}
		}
		
		return m;
	}

	public Matrix multiply(Matrix a) {
		Matrix c = new Matrix(rows, a.coloums);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < a.coloums; j++) {
				c.n[i][j] = (double) 0;
				for (int k = 0; k < a.rows; k++) {
					c.n[i][j] += n[i][k] * a.n[k][j];
				}
			}
		}
		return c;
	}

	public Matrix transpose() {
		Matrix c = new Matrix(coloums, rows);
		for (int i = 0; i < coloums; i++)
			for (int j = 0; j < rows; j++)
				c.n[i][j] = n[j][i];
		return c;
	}

	public double determinant() {

		if (rows == 1) {
			return n[0][0];
		} else if (rows == 2) {
			return n[0][0] * n[1][1] - n[1][0] * n[0][1];
		} else {

			double f = 0;
			for (int k = 0; k < coloums; k++) {
				Matrix d = new Matrix(rows - 1, coloums - 1);
				for (int j = 0; j < coloums; j++) {
					if (j < k) {
						for (int i = 1; i < rows; i++) {

							d.n[i - 1][j] = n[i][j];

						}
					}
					if(j>k){
						for (int i = 1; i < rows; i++) {

							d.n[i - 1][j-1] = n[i][j];

						}
					}

					
				}
				f += (k % 2 != 0 ? n[0][k] * (d.determinant()) * -1 : n[0][k]
						* (d.determinant()));
			}
			return f;
		}
	}

	public Matrix minor() {
		Matrix m = new Matrix(rows, coloums);
		for(int i=0;i<rows;i++){
			for(int j=0;j<coloums;j++){
				Matrix d = new Matrix(rows-1,coloums-1);
				for(int k=0;k<rows;k++){
					if(k<i){
						for(int l=0;l<coloums;l++){
							if(l<j){
								d.n[k][l]=n[k][l];
							}
							if(l>j){
								d.n[k][l-1]=n[k][l];
							}
						}
					}
					if(k>i){
						for(int l=0;l<coloums;l++){
							if(l<j){
								d.n[k-1][l]=n[k][l];
							}
							if(l>j){
								d.n[k-1][l-1]=n[k][l];
							}
						}
					}
				}
				m.n[i][j]= d.determinant();
			}
			
		}

		return m;

	}

	public Matrix adjoint() {
		Matrix m = cofactor().transpose();
		

		return m;

	}

	public Matrix cofactor() {
		Matrix m = new Matrix(rows, coloums);
		for(int i=0;i<rows;i++){
			for(int j=0;j<coloums;j++){
				Matrix d = new Matrix(rows-1,coloums-1);
				for(int k=0;k<rows;k++){
					if(k<i){
						for(int l=0;l<coloums;l++){
							if(l<j){
								d.n[k][l]=n[k][l];
							}
							if(l>j){
								d.n[k][l-1]=n[k][l];
							}
						}
					}
					if(k>i){
						for(int l=0;l<coloums;l++){
							if(l<j){
								d.n[k-1][l]=n[k][l];
							}
							if(l>j){
								d.n[k-1][l-1]=n[k][l];
							}
						}
					}
				}
				m.n[i][j]= (i+j)%2==0?d.determinant():-1*d.determinant();
			}
			
		}
		

		return m;
	}

	public Matrix inverse() {
		Matrix m = adjoint().multiply(1/determinant());	
		return m;
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < coloums; j++) {
				s = s + n[i][j] + "\t";
			}
			s = s + "\n";
		}
		return s;
	}
}
