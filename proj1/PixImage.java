/* PixImage.java */

/**
 *  The PixImage class represents an image, which is a rectangular grid of
 *  color pixels.  Each pixel has red, green, and blue intensities in the range
 *  0...255.  Descriptions of the methods you must implement appear below.
 *  They include a constructor of the form
 *
 *      public PixImage(int width, int height);
 *
 *  that creates a black (zero intensity) image of the specified width and
 *  height.  Pixels are numbered in the range (0...width - 1, 0...height - 1).
 *
 *  All methods in this class must be implemented to complete Part I.
 *  See the README file accompanying this project for additional details.
 */

public class PixImage {

  /**
   *  Define any variables associated with a PixImage object here.  These
   *  variables MUST be private.
   */
  private short[][][] img;
  private int width;
  private int height;
  private static final long[][] gx = {
		  {1, 0, -1},
		  {2, 0, -2},
		  {1, 0, -1}
  };
  private static final long[][] gy = {
		  {1, 2, 1},
		  {0, 0, 0},
		  {-1, -2, -1}
  }; 

  /**
   * isOnHeightBoundary() returns true if the coordinate (x, y) is on vertical boundary of 	 
   * the image.
   * 
   * @param x the x coordinate of the pixel.
   * @param y the y coordinate of the pixel.
   *
   * @return whether the pixel is on the vertical boundary of the image.
   */
  private boolean isOnHeightBoundary(int x, int y) {
  	return x == 0 || x == width - 1;
  }

  /**
   * isOnWidthBoundary() tests if pixel (x, y) is on the horizontal boundary of this image.
   *
   * @param x the x coordinate of the pixel.
   * @param y the y coordinate of the pixel.
   *
   * @return if the pixel is on the horizontal boundary of the image.
   */
   private boolean isOnWidthBoundary(int x, int y) {
     return y == 0 || y == height - 1;
   } 

   /**
	* numOfNeighbors() calculate the number of neigbor pixels of the pixel (x, y).
	* including the pixel itself.
	*
	* @param x the x coordinate of the pixel.
	* @param y the y coordinate of the pixel.
	*
	* @return number of neighbors.
	*/
   private int numOfNeighbors(int x, int y) {
     if(isOnWidthBoundary(x, y) && isOnHeightBoundary(x, y)) return 4;
	 if(isOnWidthBoundary(x, y) || isOnHeightBoundary(x, y)) return 6;
	 return 9;
   }
	
  /**reflectX() return the out-of-range x coordinate with reflection method.
   * 
   * @param x the x coordinate of the pixel. Precondition: -width <= x <= 2 * width - 1.
   * 
   * @return the reflecting x coordinate.
   */
  private int reflectX(int x) {
	if(x >= width) return 2 * width - 1 - x;
   	if(x < 0) return - x - 1;	
  	return x;
  }

  /**reflectY() return the out-of-range y coordinate with reflection method.
   *
   * @param y the y coordinate of the pixel, Precondition: -height <= y <= 2* height - 1.
   * 
   * @return the reflecting y coordinate.
   */
  private int reflectY(int y) {
  	if (y < 0) return - y - 1;
	if (y >= height) return 2 * height - 1 - y;
	return y;
  }
  
  /**
   * getColor() return the color of pixel (x, y), 0 for red, 1 for green, 2 for blue.
   * reflect (x, y) if out of range.
   *
   * @param x the x coordinate of the pixel.
   * @param y the y coordinate of the pixel.
   * @param color the color option.
   *
   * @return the color value of the pixel (x, y).
   */
  private short getColor(int x, int y, int color){
	x = reflectX(x);
	y = reflectY(y);
  	switch(color) {
		case 0:
			return getRed(x, y);
		case 1:
			return getGreen(x, y);
		case 2:
			return getBlue(x, y);
	}
	return -1;
  }

  /**
   * PixImage() constructs an empty PixImage with a specified width and height.
   * Every pixel has red, green, and blue intensities of zero (solid black).
   *
   * @param width the width of the image.
   * @param height the height of the image.
   */
  public PixImage(int width, int height) {
    // Your solution here.
	img = new short[width][height][3];
	this.width = width;
	this.height = height;
	for(int i = 0; i < width; i++)
		for(int j = 0; j < height; j++)
			for(int u = 0; u < 3; u++)
				img[i][j][u] = 0;
  }

  /**
   * getWidth() returns the width of the image.
   *
   * @return the width of the image.
   */
  public int getWidth() {
    // Replace the following line with your solution.
    return width;
  }

  /**
   * getHeight() returns the height of the image.
   *
   * @return the height of the image.
   */
  public int getHeight() {
    // Replace the following line with your solution.
    return height;
  }

  /**
   * getRed() returns the red intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the red intensity of the pixel at coordinate (x, y).
   */
  public short getRed(int x, int y) {
    // Replace the following line with your solution.
	if(x >= 0 && x < width && y >= 0 && y < height)
    	return img[x][y][0];
	return 0;
  }

  /**
   * getGreen() returns the green intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the green intensity of the pixel at coordinate (x, y).
   */
  public short getGreen(int x, int y) {
    // Replace the following line with your solution.
	if(x >= 0 && x < width && y >= 0 && y < height)
    	return img[x][y][1];
	return 0;
  }

  /**
   * getBlue() returns the blue intensity of the pixel at coordinate (x, y).
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @return the blue intensity of the pixel at coordinate (x, y).
   */
  public short getBlue(int x, int y) {
    // Replace the following line with your solution.
    if(x >= 0 && x < width && y >= 0 && y < height)
		return img[x][y][2];
	return 0;
  }

  /**
   * setPixel() sets the pixel at coordinate (x, y) to specified red, green,
   * and blue intensities.
   *
   * If any of the three color intensities is NOT in the range 0...255, then
   * this method does NOT change any of the pixel intensities.
   *
   * @param x the x-coordinate of the pixel.
   * @param y the y-coordinate of the pixel.
   * @param red the new red intensity for the pixel at coordinate (x, y).
   * @param green the new green intensity for the pixel at coordinate (x, y).
   * @param blue the new blue intensity for the pixel at coordinate (x, y).
   */
  public void setPixel(int x, int y, short red, short green, short blue) {
    // Your solution here.
	if (x >= 0 && x < width && y >= 0 && y < height) {
		img[x][y][0] = red;
		img[x][y][1] = green;
		img[x][y][2] = blue;
	}
  }

  /**
   * toString() returns a String representation of this PixImage.
   *
   * This method isn't required, but it should be very useful to you when
   * you're debugging your code.  It's up to you how you represent a PixImage
   * as a String.
   *
   * @return a String representation of this PixImage.
   */
  public String toString() {
    // Replace the following line with your solution.
	StringBuilder strb = new StringBuilder();
	for(int i = 0; i < width; i++) {
		for(int j = 0; j < height; j++)
			strb.append("("+img[i][j][0]+","+img[i][j][1]+","+img[i][j][2]+")");
		strb.append("\n");
	}
	return strb.toString();
  }

  /**
   * boxBlur() returns a blurred version of "this" PixImage.
   *
   * If numIterations == 1, each pixel in the output PixImage is assigned
   * a value equal to the average of its neighboring pixels in "this" PixImage,
   * INCLUDING the pixel itself.
   *
   * A pixel not on the image boundary has nine neighbors--the pixel itself and
   * the eight pixels surrounding it.  A pixel on the boundary has six
   * neighbors if it is not a corner pixel; only four neighbors if it is
   * a corner pixel.  The average of the neighbors is the sum of all the
   * neighbor pixel values (including the pixel itself) divided by the number
   * of neighbors, with non-integer quotients rounded toward zero (as Java does
   * naturally when you divide two integers).
   *
   * Each color (red, green, blue) is blurred separately.  The red input should
   * have NO effect on the green or blue outputs, etc.
   *
   * The parameter numIterations specifies a number of repeated iterations of
   * box blurring to perform.  If numIterations is zero or negative, "this"
   * PixImage is returned (not a copy).  If numIterations is positive, the
   * return value is a newly constructed PixImage.
   *
   * IMPORTANT:  DO NOT CHANGE "this" PixImage!!!  All blurring/changes should
   * appear in the new, output PixImage only.
   *
   * @param numIterations the number of iterations of box blurring.
   * @return a blurred version of "this" PixImage.
   */
  public PixImage boxBlur(int numIterations) {
    // Replace the following line with your solution.
	if (numIterations <= 0)
		return this;
	PixImage tmpImg = new PixImage(width, height);
	PixImage blrImg = new PixImage(width, height);
	for(int i = 0; i < width; i++) 
		for(int j = 0; j < height; j++)
			blrImg.setPixel(i, j, getRed(i, j), getGreen(i, j), getBlue(i, j));

	for(;numIterations > 0; numIterations--) {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				
				int r = 0, g = 0, b = 0;
				for(int u = -1; u <= 1; u++) {
					for(int v = -1; v <= 1; v++) {
						r += (int)blrImg.getRed(i + u, j + v);
						g += (int)blrImg.getGreen(i + u, j + v);
						b += (int)blrImg.getBlue(i + u, j + v);	
					}
				}
				
				int num = numOfNeighbors(i, j);
				tmpImg.setPixel(i, j, (short)(r/num), (short)(g/num), (short)(b/num));
			}
		}

		for(int i = 0; i < width; i++)
			for(int j = 0; j < height; j++) 
				blrImg.setPixel(i, j, tmpImg.getRed(i, j), tmpImg.getGreen(i, j), tmpImg.getBlue(i, j));
	}
	return blrImg;
  }

  /**
   * mag2gray() maps an energy (squared vector magnitude) in the range
   * 0...24,969,600 to a grayscale intensity in the range 0...255.  The map
   * is logarithmic, but shifted so that values of 5,080 and below map to zero.
   *
   * DO NOT CHANGE THIS METHOD.  If you do, you will not be able to get the
   * correct images and pass the autograder.
   *
   * @param mag the energy (squared vector magnitude) of the pixel whose
   * intensity we want to compute.
   * @return the intensity of the output pixel.
   */
  private static short mag2gray(long mag) {
    short intensity = (short) (30.0 * Math.log(1.0 + (double) mag) - 256.0);

    // Make sure the returned intensity is in the range 0...255, regardless of
    // the input value.
    if (intensity < 0) {
      intensity = 0;
    } else if (intensity > 255) {
      intensity = 255;
    }
    return intensity;
  }

  /**
   * sobelEdges() applies the Sobel operator, identifying edges in "this"
   * image.  The Sobel operator computes a magnitude that represents how
   * strong the edge is.  We compute separate gradients for the red, blue, and
   * green components at each pixel, then sum the squares of the three
   * gradients at each pixel.  We convert the squared magnitude at each pixel
   * into a grayscale pixel intensity in the range 0...255 with the logarithmic
   * mapping encoded in mag2gray().  The output is a grayscale PixImage whose
   * pixel intensities reflect the strength of the edges.
   *
   * See http://en.wikipedia.org/wiki/Sobel_operator#Formulation for details.
   *
   * @return a grayscale PixImage representing the edges of the input image.
   * Whiter pixels represent stronger edges.
   */
  public PixImage sobelEdges() {
    // Replace the following line with your solution.
	PixImage ret = new PixImage(width, height);

	for(int i = 0; i < width; i++) {
		for(int j = 0; j < height; j++) {
			long energy = 0;
			for(int k = 0; k < 3; k++) {
				long xx = 0, yy = 0;
				for(int u = 0; u < 3; u++) {
					for(int v = 0; v < 3; v++) {
						xx += gx[u][v] * (long)getColor(i - 1 + u, j - 1 + v, k);
						yy += gy[u][v] * (long)getColor(i - 1 + u, j - 1 + v, k);
					}
				}
				energy += xx * xx + yy * yy;
			}
			
			short val = mag2gray(energy); 
			ret.setPixel(i, j, val, val, val);
		}
	}

	return ret;
    // Don't forget to use the method mag2gray() above to convert energies to
    // pixel intensities.
  }


  /**
   * TEST CODE:  YOU DO NOT NEED TO FILL IN ANY METHODS BELOW THIS POINT.
   * You are welcome to add tests, though.  Methods below this point will not
   * be tested.  This is not the autograder, which will be provided separately.
   */


  /**
   * doTest() checks whether the condition is true and prints the given error
   * message if it is not.
   *
   * @param b the condition to check.
   * @param msg the error message to print if the condition is false.
   */
  private static void doTest(boolean b, String msg) {
    if (b) {
      System.out.println("Good.");
    } else {
      System.err.println(msg);
    }
  }

  /**
   * array2PixImage() converts a 2D array of grayscale intensities to
   * a grayscale PixImage.
   *
   * @param pixels a 2D array of grayscale intensities in the range 0...255.
   * @return a new PixImage whose red, green, and blue values are equal to
   * the input grayscale intensities.
   */
  private static PixImage array2PixImage(int[][] pixels) {
    int width = pixels.length;
    int height = pixels[0].length;
    PixImage image = new PixImage(width, height);

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        image.setPixel(x, y, (short) pixels[x][y], (short) pixels[x][y],
                       (short) pixels[x][y]);
      }
    }

    return image;
  }

  /**
   * equals() checks whether two images are the same, i.e. have the same
   * dimensions and pixels.
   *
   * @param image a PixImage to compare with "this" PixImage.
   * @return true if the specified PixImage is identical to "this" PixImage.
   */
  public boolean equals(PixImage image) {
    int width = getWidth();
    int height = getHeight();

    if (image == null ||
        width != image.getWidth() || height != image.getHeight()) {
      return false;
    }

    for (int x = 0; x < width; x++) {
      for (int y = 0; y < height; y++) {
        if (! (getRed(x, y) == image.getRed(x, y) &&
               getGreen(x, y) == image.getGreen(x, y) &&
               getBlue(x, y) == image.getBlue(x, y))) {
          return false;
        }
      }
    }
    return true;
  }

  /**
   * main() runs a series of tests to ensure that the convolutions (box blur
   * and Sobel) are correct.
   */
  public static void main(String[] args) {
    // Be forwarned that when you write arrays directly in Java as below,
    // each "row" of text is a column of your image--the numbers get
    // transposed.
    PixImage image1 = array2PixImage(new int[][] { { 0, 10, 240 },
                                                   { 30, 120, 250 },
                                                   { 80, 250, 255 } });
    System.out.println("Testing getWidth/getHeight on a 3x3 image.  " +
                       "Input image:");
    System.out.print(image1);
    doTest(image1.getWidth() == 3 && image1.getHeight() == 3,
           "Incorrect image width and height.");

    System.out.println("Testing blurring on a 3x3 image.");
    doTest(image1.boxBlur(1).equals(
           array2PixImage(new int[][] { { 40, 108, 155 },
                                        { 81, 137, 187 },
                                        { 120, 164, 218 } })),
           "Incorrect box blur (1 rep):\n" + image1.boxBlur(1));
    doTest(image1.boxBlur(2).equals(
           array2PixImage(new int[][] { { 91, 118, 146 },
                                        { 108, 134, 161 },
                                        { 125, 151, 176 } })),
           "Incorrect box blur (2 rep):\n" + image1.boxBlur(2));
    doTest(image1.boxBlur(2).equals(image1.boxBlur(1).boxBlur(1)),
           "Incorrect box blur (1 rep + 1 rep):\n" +
           image1.boxBlur(2) + image1.boxBlur(1).boxBlur(1));

    System.out.println("Testing edge detection on a 3x3 image.");
    doTest(image1.sobelEdges().equals(
           array2PixImage(new int[][] { { 104, 189, 180 },
                                        { 160, 193, 157 },
                                        { 166, 178, 96 } })),
           "Incorrect Sobel:\n" + image1.sobelEdges());


    PixImage image2 = array2PixImage(new int[][] { { 0, 100, 100 },
                                                   { 0, 0, 100 } });
    System.out.println("Testing getWidth/getHeight on a 2x3 image.  " +
                       "Input image:");
    System.out.print(image2);
    doTest(image2.getWidth() == 2 && image2.getHeight() == 3,
           "Incorrect image width and height.");

    System.out.println("Testing blurring on a 2x3 image.");
    doTest(image2.boxBlur(1).equals(
           array2PixImage(new int[][] { { 25, 50, 75 },
                                        { 25, 50, 75 } })),
           "Incorrect box blur (1 rep):\n" + image2.boxBlur(1));

    System.out.println("Testing edge detection on a 2x3 image.");
    doTest(image2.sobelEdges().equals(
           array2PixImage(new int[][] { { 122, 143, 74 },
                                        { 74, 143, 122 } })),
           "Incorrect Sobel:\n" + image2.sobelEdges());
  }
}
