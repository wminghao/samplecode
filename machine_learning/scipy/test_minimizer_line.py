import pandas as pd
import matplotlib.pyplot as plt
import numpy as np
import scipy.optimize as spo

## predict a line from a bunch of dots in 2 d chart

def error(line, data):
    #metric: Sum of squared Y-axis differences
    err = np.sum((data[:,1]-(line[0]*data[:,0]+line[1])) ** 2)
    return err

#minizier functions
def fit_line(data, error_func):
    l = np.float32([0,np.mean(data[:,1])]) #initial guess, slope =0, intercept = mean(y values
    
    #minize function, linear regression:  Minimize a scalar function of one or more variables using Sequential Least SQuares Programming (SLSQP).
    #https://docs.scipy.org/doc/scipy-0.14.0/reference/generated/scipy.optimize.minimize.html
    result = spo.minimize(error_func, l, args=(data,), method='SLSQP', options = {'disp': True})
    return result.x

def test_run():
    # define original line, slope = 2, c0=4
    l_orig =np.float32([4,2])
    print "Original line: C0={}, C1={}".format(l_orig[0],l_orig[1])
    Xorig = np.linspace(0,10,21) #line from 10 to 21
    Yorig = l_orig[0] * Xorig + l_orig[1]
    plt.plot(Xorig, Yorig, 'b--', linewidth=2.0, label="Original line")

    #generate noise data
    noise_sigma = 3.0
    noise = np.random.normal(0, noise_sigma, Yorig.shape)
    data = np.asarray([Xorig, Yorig+noise]).T
    plt.plot(data[:,0], data[:,1], 'go', label="Data points")
    
    #Try to fit a line to this data
    l_fit = fit_line(data, error)
    print "Fitted line: C0={}, C1={}".format(l_fit[0],l_fit[1])
    plt.plot(data[:,0], l_fit[0]*data[:,0]+l_fit[1], 'r--', linewidth=2.0, label="Data points")
    
    #show plot
    plt.show()

if __name__ == "__main__":
    test_run()
