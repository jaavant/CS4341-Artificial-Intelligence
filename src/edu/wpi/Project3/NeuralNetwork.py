import time
from keras.models import Sequential
from keras.layers import Dense, Activation
import numpy as np
import matplotlib.pyplot as plt
from random import randint
import itertools



images_array = np.load('images.npy')
labels = np.load('labels.npy')

num_images, x , y = images_array.shape
images_array = images_array.reshape((num_images, y * x))

hot_vector_list = []

for num in labels:
    hot_vector_list.append(np.eye(10)[num])
hot_vector_array = np.asarray(hot_vector_list)


##loop through alll seperate into groups based on number... genrate random number and put into test, validation, train

numImages = [[],[],[],[],[],[],[],[],[],[]]
nums = [[],[],[],[],[],[],[],[],[],[]]

for num in range(0,len(images_array)):
    if np.array_equal(hot_vector_array[num], np.eye(10)[0]):
        numImages[0].append(images_array[num])
        nums[0].append(hot_vector_array[num])
    elif np.array_equal(hot_vector_array[num], np.eye(10)[1]):
        numImages[1].append(images_array[num])
        nums[1].append(hot_vector_array[num])
    elif np.array_equal(hot_vector_array[num], np.eye(10)[2]):
        numImages[2].append(images_array[num])
        nums[2].append(hot_vector_array[num])
    elif np.array_equal(hot_vector_array[num], np.eye(10)[3]):
        numImages[3].append(images_array[num])
        nums[3].append(hot_vector_array[num])
    elif np.array_equal(hot_vector_array[num], np.eye(10)[4]):
        numImages[4].append(images_array[num])
        nums[4].append(hot_vector_array[num])
    elif np.array_equal(hot_vector_array[num], np.eye(10)[5]):
        numImages[5].append(images_array[num])
        nums[5].append(hot_vector_array[num])
    elif np.array_equal(hot_vector_array[num], np.eye(10)[6]):
        numImages[6].append(images_array[num])
        nums[6].append(hot_vector_array[num])
    elif np.array_equal(hot_vector_array[num], np.eye(10)[7]):
        numImages[7].append(images_array[num])
        nums[7].append(hot_vector_array[num])
    elif np.array_equal(hot_vector_array[num], np.eye(10)[8]):
        numImages[8].append(images_array[num])
        nums[8].append(hot_vector_array[num])
    elif np.array_equal(hot_vector_array[num], np.eye(10)[9]):
        numImages[9].append(images_array[num])
        nums[9].append(hot_vector_array[num])

testSet = []
testNumSet = []

validSet = []
validNumSet = []

trainingSet = []
trainingNumSet = []


for j in range(0,10):
    for k in range(0,len(numImages[j])):
        rdm = randint(1,100)
        if rdm <= 15:
            validSet.append(numImages[j][k])
            validNumSet.append(nums[j][k])
        elif rdm <= 40:
            testSet.append(numImages[j][k])
            testNumSet.append(nums[j][k])
        else:
            trainingSet.append(numImages[j][k])
            trainingNumSet.append(nums[j][k])

x_test = np.asarray(testSet)
y_test = np.asarray(testNumSet)
x_val = np.asarray(validSet)
y_val = np.asarray(validNumSet)
x_train = np.asarray(trainingSet)
y_train = np.asarray(trainingNumSet)


#Function
def predictedNum(npArray):
   maxIndex = 0
   max = -999999999

   for num in range(0,len(npArray)):
       if npArray[num] > max:
           maxIndex = num
           max = npArray[num]

   return maxIndex



#Set seed
np.random.seed(7)

# Model Template

model = Sequential() # declare model
model.add(Dense(10, input_shape=(28*28, ), kernel_initializer='he_normal')) # first layer
model.add(Activation('relu'))
#
#
model.add(Dense(397, kernel_initializer='he_normal'))
model.add(Activation('tanh'))
#
#
model.add(Dense(10, kernel_initializer='he_normal')) # last layer
model.add(Activation('softmax'))


# Compile Model
model.compile(optimizer='sgd',
              loss='categorical_crossentropy',
              metrics=['accuracy'])

# Train Model
history = model.fit(x_train, y_train,
                    validation_data = (x_val, y_val),
                    epochs = 25,
                    batch_size=512)


# Report Results
scores = model.predict(x_test)


#Confusion Matrix
cMatrix = np.zeros((10,10))

for num in range(0,len(y_test)):
    if np.array_equal(y_test[num], np.eye(10)[0]):
        cMatrix[0,predictedNum(scores[num])] += 1
    elif np.array_equal(y_test[num], np.eye(10)[1]):
        cMatrix[1, predictedNum(scores[num])] += 1
    elif np.array_equal(y_test[num], np.eye(10)[2]):
        cMatrix[2, predictedNum(scores[num])] += 1
    elif np.array_equal(y_test[num], np.eye(10)[3]):
        cMatrix[3, predictedNum(scores[num])] += 1
    elif np.array_equal(y_test[num], np.eye(10)[4]):
        cMatrix[4, predictedNum(scores[num])] += 1
    elif np.array_equal(y_test[num], np.eye(10)[5]):
        cMatrix[5, predictedNum(scores[num])] += 1
    elif np.array_equal(y_test[num], np.eye(10)[6]):
        cMatrix[6, predictedNum(scores[num])] += 1
    elif np.array_equal(y_test[num], np.eye(10)[7]):
        cMatrix[7, predictedNum(scores[num])] += 1
    elif np.array_equal(y_test[num], np.eye(10)[8]):
        cMatrix[8, predictedNum(scores[num])] += 1
    elif np.array_equal(y_test[num], np.eye(10)[9]):
        cMatrix[9, predictedNum(scores[num])] += 1


print(scores.shape)
print(scores[0])
print(cMatrix)




