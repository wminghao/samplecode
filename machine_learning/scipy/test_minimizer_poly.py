"""==============================================================================="""

"""Minimize an objective function using SciPy: 3D"""

import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
import scipy.optimize as spo

def error_poly(C, data): # error function
    """Compute error between given polynomial and observed data.
    
    Parameters
    ----------
    C: numpy.poly1d object or equivalent array representing polynomial coefficients
    data: 2D array where each row is a point (x, y)
    
    Returns error as a single real value.
    """
    # Metric: Sum of squared Y-axis differences
    err = np.sum((data[:,1] - np.polyval(C, data[:,0])) ** 2)
    return err
    
def fit_poly(data, error_func, degree=3):
    """Fit a polynomial to given data, using a supplied error function.

    Parameters
    ----------
    data: 2D array where each row is a point (X0, Y)
    error_func: function that computes the error between a polynomial and observed data

    Returns polynomial that minimizes the error function.
    """
    # Generate initial guess for line model (all coeffs = 1)
    Cguess = np.poly1d(np.ones(degree + 1, dtype=np.float32))

    # Plot initial guess (optional)
    x = np.linspace(-5, 5, 21)
    plt.plot(x, np.polyval(Cguess, x), 'm--', linewidth=2.0, label = "Initial guess")

    # Call optimizer to minimize error function
    result = spo.minimize(error_func, Cguess, args=(data,), method = 'SLSQP', options={'disp': True})
    return np.poly1d(result.x) # convert optimal result into a poly1d object and return

def test_run():
    # Define original line
    # Generate noisy data points
    # Try to fit a line to this data
    # Add a legend and show plot
    
if __name__ == "__main__":
    test_run()
