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
    #add layer with no activation
    model.add(Dense(50, input_dim=1, activation="tanh", init='he_normal'))
    model.add(Dense(20, input_dim=50, activation="tanh", init="he_normal"))
    model.add(Dense(30, input_dim=20, activation="tanh", init="he_normal"))
    model.add(Dense(1, input_dim=30, activation='linear', init='he_normal'))
    model.compile(loss='mean_squared_error', optimizer=SGD(lr=0.01, momentum=0.9, nesterov=True))
    return model

model = baseline_model()
#train model
model.fit(x, y, nb_epoch=50, verbose = 1)

#Draw graphs. Black - actual data, magenta - predicted data.
plt.scatter(x, y, color='black', antialiased=True)
plt.plot(x, model.predict(x), color='magenta', linewidth=2, antialiased=True)
plt.show()

for layer in model.layers:
    weights = layer.get_weights()
    print "Layer: ", layer.__repr__(), "    Weight: ", weights
