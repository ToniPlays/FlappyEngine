package Maths;

import java.nio.FloatBuffer;

import org.lwjgl.system.MemoryUtil;

public class Matrix4f
{
	public static final int SIZE = 4;
	private float[][] m;
	
	public Matrix4f()
	{
		m = new float[SIZE][SIZE];
	}

	public Matrix4f InitIdentity()
	{
		m[0][0] = 1;	m[0][1] = 0;	m[0][2] = 0;	m[0][3] = 0;
		m[1][0] = 0;	m[1][1] = 1;	m[1][2] = 0;	m[1][3] = 0;
		m[2][0] = 0;	m[2][1] = 0;	m[2][2] = 1;	m[2][3] = 0;
		m[3][0] = 0;	m[3][1] = 0;	m[3][2] = 0;	m[3][3] = 1;

		return this;
	}
	
	public Matrix4f InitTranslation(Vector3 v)
	{
		m[0][0] = 1;	m[0][1] = 0;	m[0][2] = 0;	m[0][3] = v.x;
		m[1][0] = 0;	m[1][1] = 1;	m[1][2] = 0;	m[1][3] = v.y;
		m[2][0] = 0;	m[2][1] = 0;	m[2][2] = 1;	m[2][3] = v.z;
		m[3][0] = 0;	m[3][1] = 0;	m[3][2] = 0;	m[3][3] = 1;
		
		return this;
	}
	
	public Matrix4f InitRotation(Vector3 r)
	{
		Matrix4f rx = new Matrix4f();
		Matrix4f ry = new Matrix4f();
		Matrix4f rz = new Matrix4f();
		
		r.x = (float)Math.toRadians(r.x);
		r.y = (float)Math.toRadians(r.y);
		r.z = (float)Math.toRadians(r.z);
		
		rz.m[0][0] = (float)Math.cos(r.z);rz.m[0][1] = -(float)Math.sin(r.z);rz.m[0][2] = 0;				rz.m[0][3] = 0;
		rz.m[1][0] = (float)Math.sin(r.z);rz.m[1][1] = (float)Math.cos(r.z);rz.m[1][2] = 0;					rz.m[1][3] = 0;
		rz.m[2][0] = 0;					rz.m[2][1] = 0;					rz.m[2][2] = 1;					rz.m[2][3] = 0;
		rz.m[3][0] = 0;					rz.m[3][1] = 0;					rz.m[3][2] = 0;					rz.m[3][3] = 1;
		
		rx.m[0][0] = 1;					rx.m[0][1] = 0;					rx.m[0][2] = 0;					rx.m[0][3] = 0;
		rx.m[1][0] = 0;					rx.m[1][1] = (float)Math.cos(r.x);rx.m[1][2] = -(float)Math.sin(r.x);rx.m[1][3] = 0;
		rx.m[2][0] = 0;					rx.m[2][1] = (float)Math.sin(r.x);rx.m[2][2] = (float)Math.cos(r.x);rx.m[2][3] = 0;
		rx.m[3][0] = 0;					rx.m[3][1] = 0;					rx.m[3][2] = 0;					rx.m[3][3] = 1;
		
		ry.m[0][0] = (float)Math.cos(r.y);ry.m[0][1] = 0;					ry.m[0][2] = -(float)Math.sin(r.y);ry.m[0][3] = 0;
		ry.m[1][0] = 0;					ry.m[1][1] = 1;					ry.m[1][2] = 0;					ry.m[1][3] = 0;
		ry.m[2][0] = (float)Math.sin(r.y);ry.m[2][1] = 0;					ry.m[2][2] = (float)Math.cos(r.y);ry.m[2][3] = 0;
		ry.m[3][0] = 0;					ry.m[3][1] = 0;					ry.m[3][2] = 0;					ry.m[3][3] = 1;
		
		m = rz.Mul(ry.Mul(rx)).GetM();
		
		return this;
	}
	
	public Matrix4f InitScale(float x, float y, float z)
	{
		m[0][0] = x;	m[0][1] = 0;	m[0][2] = 0;	m[0][3] = 0;
		m[1][0] = 0;	m[1][1] = y;	m[1][2] = 0;	m[1][3] = 0;
		m[2][0] = 0;	m[2][1] = 0;	m[2][2] = z;	m[2][3] = 0;
		m[3][0] = 0;	m[3][1] = 0;	m[3][2] = 0;	m[3][3] = 1;
		
		return this;
	}
	
	public Matrix4f InitPerspective(float fov, float aspectRatio, float zNear, float zFar)
	{
		float tanHalfFOV = (float)Math.tan(fov / 2);
		float zRange = zNear - zFar;
		
		m[0][0] = 1.0f / (tanHalfFOV * aspectRatio);	m[0][1] = 0;					m[0][2] = 0;	m[0][3] = 0;
		m[1][0] = 0;						m[1][1] = 1.0f / tanHalfFOV;	m[1][2] = 0;	m[1][3] = 0;
		m[2][0] = 0;						m[2][1] = 0;					m[2][2] = (-zNear -zFar)/zRange;	m[2][3] = 2 * zFar * zNear / zRange;
		m[3][0] = 0;						m[3][1] = 0;					m[3][2] = 1;	m[3][3] = 0;
		
		
		return this;
	}

	public Matrix4f InitOrthographic(float left, float right, float bottom, float top, float near, float far)
	{
		float width = right - left;
		float height = top - bottom;
		float depth = far - near;

		m[0][0] = 2/width;m[0][1] = 0;	m[0][2] = 0;	m[0][3] = -(right + left)/width;
		m[1][0] = 0;	m[1][1] = 2/height;m[1][2] = 0;	m[1][3] = -(top + bottom)/height;
		m[2][0] = 0;	m[2][1] = 0;	m[2][2] = -2/depth;m[2][3] = -(far + near)/depth;
		m[3][0] = 0;	m[3][1] = 0;	m[3][2] = 0;	m[3][3] = 1;

		return this;
	}

	public Matrix4f InitRotation(Vector3 forward, Vector3 up)
	{
		forward.Normalized();
		
		up.Normalized();
		up = up.Cross(forward);
		
		Vector3 u = forward.Cross(up);

		return InitRotation(forward, u, up);
	}

	public Matrix4f InitRotation(Vector3 forward, Vector3 up, Vector3 right)
	{
		Vector3 f = forward;
		Vector3 r = right;
		Vector3 u = up;

		m[0][0] = r.x;	m[0][1] = r.y;	m[0][2] = r.z;	m[0][3] = 0;
		m[1][0] = u.x;	m[1][1] = u.y;	m[1][2] = u.z;	m[1][3] = 0;
		m[2][0] = f.x;	m[2][1] = f.y;	m[2][2] = f.z;	m[2][3] = 0;
		m[3][0] = 0;		m[3][1] = 0;		m[3][2] = 0;		m[3][3] = 1;

		return this;
	}

	public Vector3 Transform(Vector3 r)
	{
		return new Vector3(m[0][0] * r.x + m[0][1] * r.y + m[0][2] * r.z + m[0][3],
		                    m[1][0] * r.x + m[1][1] * r.y + m[1][2] * r.z + m[1][3],
		                    m[2][0] * r.x + m[2][1] * r.y + m[2][2] * r.z + m[2][3]);
	}
	
	public Matrix4f Mul(Matrix4f r)
	{
		Matrix4f res = new Matrix4f();
		
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				res.Set(i, j, m[i][0] * r.Get(0, j) +
						m[i][1] * r.Get(1, j) +
						m[i][2] * r.Get(2, j) +
						m[i][3] * r.Get(3, j));
			}
		}
		
		return res;
	}
	
	public float[][] GetM()
	{
		float[][] res = new float[4][4];
		
		for(int i = 0; i < 4; i++)
			for(int j = 0; j < 4; j++)
				res[i][j] = m[i][j];
		
		return res;
	}
	
	public float Get(int x, int y)
	{
		return m[x][y];
	}

	public void SetM(float[][] m)
	{
		this.m = m;
	}
	
	public void Set(int x, int y, float value)
	{
		m[x][y] = value;
	}

	public static FloatBuffer getAll(Matrix4f value) {
		
		FloatBuffer buffer = MemoryUtil.memAllocFloat(SIZE * SIZE);
		
		for (int i = 0; i < SIZE; i++) {
			for (int j = 0; j < SIZE; j++) {
				buffer.put(value.Get(i, j));
			}
		}
		
		buffer.flip();
		return buffer;
	}
}