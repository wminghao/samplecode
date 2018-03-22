import numpy as np

def test_run():
    print np.array([(2,3,4), (5,6,7)])
    print np.ones((3,4,5), dtype=np.int_)
    print np.random.rand(5,3)
    #generate normalized random number with mean=50, standard deviation=10
    b = np.random.normal(50,10,size=(5,3))
    print b
    print b[1,1] # access the element at position (1,1)
    print b[0,1:3] # print a sub array
    print b[:,0:3:2] #access all rows with columns 0,2 for every row, step of 2
    b[:,2] = 2 # modify column 3 of all rows to be 2
    print b

    #access rows and columns
    a = np.random.random((4,5)) # 4*5 array of random numbers
    print a.shape[0] # num of rows
    print a.shape[1] # num of cols
    print a.size
    print a
    print a.dtype # data type
    print "Sum:",a.sum()
    print "Sum rows:",a.sum(axis=0) # Sum each row
    print "Mean of all array", a.mean()

    #access the index of an array
    indices = np.array([1,1,2,3])
    c = np.random.rand(5)
    print c
    print c[indices]

    #access from boolean arrays
    mean = c.mean()
    print "Mean:", mean
    print "Lower than mean",c[c<mean]


if __name__ == "__main__":
    test_run()
