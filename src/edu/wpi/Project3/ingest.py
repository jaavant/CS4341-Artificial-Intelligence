import numpy as np

images_array = np.load('images.npy')
labels = np.load('labels.npy')

num_images, x , y = images_array.shape
images_array = images_array.reshape((num_images, y * x))

hot_vector_list = []

for num in labels:
    hot_vector_list.append(np.eye(10)[num])

hot_vector_array = np.asarray(hot_vector_list)


##loop through alll seperate into groups based on number... genrate random number and put into test, validation, train

numImages0 = []
num0 = []
numImages1 = []
num1 = []
numImages2 = []
num2 = []
numImages3 = []
num3 = []
numImages4 = []
num4 = []
numImages5 = []
num5 = []
numImages6 = []
num6 = []
numImages7 = []
num7 = []
numImages8 = []
num8 = []
numImages9 = []
num9 = []

for num in range(0,len(images_array)):
    if hot_vector_array[num] == np.eye(10)[0]:
        numImages0.append(images_array[num])
        num0.append(hot_vector_array[num])
    elif hot_vector_array[num] == np.eye(10)[1]:
        numImages1.append(images_array[num])
        num1.append(hot_vector_array[num])
    elif hot_vector_array[num] == np.eye(10)[2]:
        numImages2.append(images_array[num])
        num2.append(hot_vector_array[num])
    elif hot_vector_array[num] == np.eye(10)[3]:
        numImages3.append(images_array[num])
        num3.append(hot_vector_array[num])
    elif hot_vector_array[num] == np.eye(10)[4]:
        numImages4.append(images_array[num])
        num4.append(hot_vector_array[num])
    elif hot_vector_array[num] == np.eye(10)[5]:
        numImages5.append(images_array[num])
        num5.append(hot_vector_array[num])
    elif hot_vector_array[num] == np.eye(10)[6]:
        numImages6.append(images_array[num])
        num6.append(hot_vector_array[num])
    elif hot_vector_array[num] == np.eye(10)[7]:
        numImages7.append(images_array[num])
        num7.append(hot_vector_array[num])
    elif hot_vector_array[num] == np.eye(10)[8]:
        numImages8.append(images_array[num])
        num8.append(hot_vector_array[num])
    elif hot_vector_array[num] == np.eye(10)[9]:
        numImages9.append(images_array[num])
        num9.append(hot_vector_array[num])


testSet = []
validSet = []
trainingSet = []







