
public class Body {
	
	private double myXPos;
	private double myYPos;
	private double myXVel;
	private double myYVel;
	private double myMass;
	private String myFileName; 
	
	/**
	 * 
	 * @param xp
	 * @param yp
	 * @param xv
	 * @param yv
	 * @param mass
	 * @param filename
	 */
	public Body (double xp, double yp, double xv, 
			double yv, double mass, String filename) {
		myXPos = xp;
		myYPos = yp;
		myXVel = xv; 
		myYVel = yv;
		myMass = mass;
		myFileName = filename; 
	}

	/**
	 * 
	 * @param b
	 */
	public Body(Body b) {
		myXPos = b.myXPos;
		myYPos = b.myYPos;
		myXVel = b.myXVel;
		myYVel = b.myYVel;
		myMass = b.myMass;
		myFileName = b.myFileName; 
	}
	
	/**
	 * 
	 * @param b
	 * @return
	 */
	public double calcDistance (Body b) {
		return Math.sqrt((this.myXPos - b.myXPos)*(this.myXPos - b.myXPos) + 
				(this.myYPos - b.myYPos)*(this.myYPos - b.myYPos));
	}
	
	/**
	 * 
	 * @param b
	 * @return
	 */
	public double calcForceExertedBy(Body b) {
		double G = 6.67*1e-11; 
		double F = G*(this.myMass)*(b.myMass) / (Math.pow(this.calcDistance(b),2));
		return F;
	}
	
	/**
	 * 
	 * @param b
	 * @return
	 */
	public double calcForceExertedByX(Body b) {
		double Fx = this.calcForceExertedBy(b)*(b.myXPos-this.myXPos)/
				this.calcDistance(b);
		return Fx;
	}

	/**
	 * 
	 * @param b
	 * @return
	 */
	public double calcForceExertedByY(Body b) {
		double Fy = this.calcForceExertedBy(b)*(b.myYPos-this.myYPos)/
				this.calcDistance(b);
		return Fy;
	}
	
	
	/**
	 * 
	 * @param bodies
	 * @return
	 */
	public double calcNetForceExertedByX(Body[] bodies) {
		double FbyX = 0.0;
		for (Body b : bodies) {
			if(! b.equals(this)) {
				FbyX += calcForceExertedByX(b);
			}
		}
		return FbyX;
	}
	
	/**
	 * 
	 * @param bodies
	 * @return
	 */
	public double calcNetForceExertedByY(Body[] bodies) {
		double FbyY = 0.0;
		for (Body b : bodies) {
			if(! b.equals(this)) {
				FbyY += calcForceExertedByY(b);
			}
		}
		return FbyY;
	}
	
	/**
	 * 
	 * @param deltaT
	 * @param xforce
	 * @param yforce
	 */
	public void update(double deltaT, 
			double xforce, double yforce) {
		double ax = xforce / this.myMass;
		double ay = yforce / this.myMass;
		double nvx = this.myXVel + deltaT*ax;
		double nvy = this.myYVel + deltaT*ay;
		double nx = this.myXPos + deltaT*nvx;
		double ny = this.myYPos + deltaT*nvy;
		this.myXPos = nx;
		this.myYPos = ny;
		this.myXVel = nvx;
		this.myYVel = nvy;
	}
	
	/**
	 * 
	 */
	public void draw() {
		StdDraw.picture(this.myXPos, this.myYPos, "images/"+this.myFileName);
	}
	
	/**
	 * 
	 * @return
	 */
	public double getX() {
		return myXPos;
	}

	/**
	 * 
	 * @return
	 */
	public double getY() {
		return myYPos;
	}

	/**
	 * 
	 * @return
	 */
	public double getXVel() {
		return myXVel;
	}

	/**
	 * 
	 * @return
	 */
	public double getYVel() {
		return myYVel;
	}

	/**
	 * 
	 * @return
	 */
	public double getMass() {
		return myMass;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getName() {
		return myFileName;
	}


}

