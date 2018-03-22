import matplotlib.pyplot as plt
import numpy as np

#create 1000 values for x, from -3 to 3, looks like[[x1], [x2], [x3]......]
x = np.linspace(-3, 3, 1000).reshape(-1, 1)

def f1(input):
    return 2 * input + 5

def f2(x):
    return 2 * np.sin(x) + 5

def f(x):
    return x * np.sin(x * 2 * np.pi) if x < 0 else -x * np.sin(x * np.pi) + np.exp(x / 2) - np.exp(0)

#make the function to work with vector instead of single input
f = np.vectorize(f)
y = f(x)

from keras.models import Sequential
from keras.layers import Dense
from keras.optimizers import SGD

def baseline_model():
    #create linear stack of layers
    model = Sequential()

    #add 4 layers with activation
    #input_dim = # of neurons in the layer, intermediatory alpha function = tanh(tangent), final function = linear function. he_normal = he normal initializer of Theta
    model.add(Dense(50, input_dim=1, activation="tanh", init='he_normal'))
    model.add(Dense(20, input_dim=50, activation="tanh", init="he_normal"))
    model.add(Dense(30, input_dim=20, activation="tanh", init="he_normal"))
    model.add(Dense(1, input_dim=30, activation='linear', init='he_normal'))

    #optimizer = SGD(stochastic gradient descent), learning rate = 0.01, momentum=0.9
    model.compile(loss='mean_squared_error', optimizer=SGD(lr=0.01, momentum=0.9, nesterov=True))
    return model

model = baseline_model()

#train model, nb_epoch = # of iterations, the more the better
model.fit(x, y, nb_epoch=1000, verbose = 1)

#Draw graphs. Black - actual data, magenta - predicted data.
plt.scatter(x, y, color='black', antialiased=True)
plt.plot(x, model.predict(x), color='magenta', linewidth=2, antialiased=True)
plt.show()

#Print out the weights, last layer is the final linear weights
for layer in model.layers:
    weights = layer.get_weights()
    print "Layer: ", layer.__repr__(), "    Weight: ", weights

#The number of features is decided automatically by the model. not 1 feature(x), but a variation of x, like, x^2, cos(x), sin(x)
#We don't need to work directly with theta
#Only need to run model.predict(x) for any input x

