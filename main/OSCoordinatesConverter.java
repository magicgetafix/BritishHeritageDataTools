package main;

public class OSCoordinatesConverter {

		private final double AA = 6377563.396;
		private final double BB = 6356256.91;
		private final double E2 = (AA - BB) / AA;
		private final double N0 = -100000;
		private final double E0 = 400000;
		private final double F0 = 0.999601272;
		private final double PHI0 = 0.855211333;
		private final double LAMBDA0 = -0.034906585;
		private final double N = (AA - BB) / (AA + BB);

		public static double lat, lng;
		
		/**This method returns a LatLon object which contains both a latitude and longitude as values
		 * 
		 * It converts UK Ordnance Survey coordinates expressed as "northings" and "eastings" and 
		 * returns latitude and longitude coordinates which can be used by GPS devices and googlemaps
		 * to pinpoint locations on a globe rather than a grid
		 * 
		 * 
		 * @param easting a double which is the easting coordinate
		 * @param northing a double which is the northing coordinate
		 * @return an object of type LatLon containing both latitude and longitude
		 */
		public static LatLon convertToLatLon(double easting, double northing){
			
			RefEll airy1830 = new RefEll(6377563.396, 6356256.909);
			double OSGB_F0 = 0.9996012717;
			double N0 = -100000.0;
			double E0 = 400000.0;
			double phi0 = Deg2Rad(49.0);
			double lambda0 = Deg2Rad(-2.0);
			double a = airy1830.maj;
			double b = airy1830.min;
			double eSquared = airy1830.ecc;
			double phi = 0.0;
			double lambda = 0.0;
			double E = easting;
			double N = northing;
			double n = (a - b) / (a + b);
			double M = 0.0;
			double phiPrime = ((N - N0) / (a * OSGB_F0)) + phi0;
			do
			{
				M =
				  (b * OSGB_F0)
					* (((1 + n + ((5.0 / 4.0) * n * n) + ((5.0 / 4.0) * n * n * n))
					  * (phiPrime - phi0))
					  - (((3 * n) + (3 * n * n) + ((21.0 / 8.0) * n * n * n))
						* Math.sin(phiPrime - phi0)
						* Math.cos(phiPrime + phi0))
					  + ((((15.0 / 8.0) * n * n) + ((15.0 / 8.0) * n * n * n))
						* Math.sin(2.0 * (phiPrime - phi0))
						* Math.cos(2.0 * (phiPrime + phi0)))
					  - (((35.0 / 24.0) * n * n * n)
						* Math.sin(3.0 * (phiPrime - phi0))
						* Math.cos(3.0 * (phiPrime + phi0))));
				phiPrime += (N - N0 - M) / (a * OSGB_F0);
			} while ((N - N0 - M) >= 0.001);
			double v = a * OSGB_F0 * Math.pow(1.0 - eSquared * SinSquared(phiPrime), -0.5);
			double rho =
			  a
				* OSGB_F0
				* (1.0 - eSquared)
				* Math.pow(1.0 - eSquared * SinSquared(phiPrime), -1.5);
			double etaSquared = (v / rho) - 1.0;
			double VII = Math.tan(phiPrime) / (2 * rho * v);
			double VIII =
			  (Math.tan(phiPrime) / (24.0 * rho * Math.pow(v, 3.0)))
				* (5.0
				  + (3.0 * TanSquared(phiPrime))
				  + etaSquared
				  - (9.0 * TanSquared(phiPrime) * etaSquared));
			double IX =
			  (Math.tan(phiPrime) / (720.0 * rho * Math.pow(v, 5.0)))
				* (61.0
				  + (90.0 * TanSquared(phiPrime))
				  + (45.0 * TanSquared(phiPrime) * TanSquared(phiPrime)));
			double X = Sec(phiPrime) / v;
			double XI =
			  (Sec(phiPrime) / (6.0 * v * v * v))
				* ((v / rho) + (2 * TanSquared(phiPrime)));
			double XII =
			  (Sec(phiPrime) / (120.0 * Math.pow(v, 5.0)))
				* (5.0
				  + (28.0 * TanSquared(phiPrime))
				  + (24.0 * TanSquared(phiPrime) * TanSquared(phiPrime)));
			double XIIA =
			  (Sec(phiPrime) / (5040.0 * Math.pow(v, 7.0)))
				* (61.0
				  + (662.0 * TanSquared(phiPrime))
				  + (1320.0 * TanSquared(phiPrime) * TanSquared(phiPrime))
				  + (720.0
					* TanSquared(phiPrime)
					* TanSquared(phiPrime)
					* TanSquared(phiPrime)));
			phi =
			  phiPrime
				- (VII * Math.pow(E - E0, 2.0))
				+ (VIII * Math.pow(E - E0, 4.0))
				- (IX * Math.pow(E - E0, 6.0));
			lambda =
			  lambda0
				+ (X * (E - E0))
				- (XI * Math.pow(E - E0, 3.0))
				+ (XII * Math.pow(E - E0, 5.0))
				- (XIIA * Math.pow(E - E0, 7.0));


			lat = Rad2Deg(phi);
			lng = Rad2Deg(lambda);
			// convert to WGS84
			OSGB36ToWGS84();

			return new LatLon(lat, lng);
		}
		
		private static double Deg2Rad(double x)
		{
			return x * (Math.PI / 180);
		}

		private static double Rad2Deg(double x)
		{
			return x * (180 / Math.PI);
		}

		private static double SinSquared(double x)
		{
			return Math.sin(x) * Math.sin(x);
		}

		private static double TanSquared(double x)
		{
			return Math.tan(x) * Math.tan(x);
		}

		private static double Sec(double x)
		{
			return 1.0 / Math.cos(x);
		}

		private static void OSGB36ToWGS84()
		{
			RefEll airy1830 = new RefEll(6377563.396, 6356256.909);
			double a = airy1830.maj;
			double b = airy1830.min;
			double eSquared = airy1830.ecc;
			double phi = Deg2Rad(lat);
			double lambda = Deg2Rad(lng);
			double v = a / (Math.sqrt(1 - eSquared * SinSquared(phi)));
			double H = 0; // height
			double x = (v + H) * Math.cos(phi) * Math.cos(lambda);
			double y = (v + H) * Math.cos(phi) * Math.sin(lambda);
			double z = ((1 - eSquared) * v + H) * Math.sin(phi);

			double tx = 446.448;
			double ty = -124.157;
			double tz = 542.060;
			double s = -0.0000204894;
			double rx = Deg2Rad(0.00004172222);
			double ry = Deg2Rad(0.00006861111);
			double rz = Deg2Rad(0.00023391666);

			double xB = tx + (x * (1 + s)) + (-rx * y) + (ry * z);
			double yB = ty + (rz * x) + (y * (1 + s)) + (-rx * z);
			double zB = tz + (-ry * x) + (rx * y) + (z * (1 + s));

			RefEll wgs84 = new RefEll(6378137.000, 6356752.3141);
			a = wgs84.maj;
			b = wgs84.min;
			eSquared = wgs84.ecc;

			double lambdaB = Rad2Deg(Math.atan(yB / xB));
			double p = Math.sqrt((xB * xB) + (yB * yB));
			double phiN = Math.atan(zB / (p * (1 - eSquared)));
			for (int i = 1; i < 10; i++)
			{
				v = a / (Math.sqrt(1 - eSquared * SinSquared(phiN)));
				double phiN1 = Math.atan((zB + (eSquared * v * Math.sin(phiN))) / p);
				phiN = phiN1;
			}

			double phiB = Rad2Deg(phiN);

			lat = phiB;
			lng = lambdaB;
		}
	}

	


