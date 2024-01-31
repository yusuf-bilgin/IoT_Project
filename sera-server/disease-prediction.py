from fastapi import FastAPI, File, UploadFile
from fastapi.middleware.cors import CORSMiddleware
import uvicorn  # For run web app async
import numpy as np
from io import BytesIO
from PIL import Image
import tensorflow as tf
import os

app = FastAPI()

origins = [
    "http://localhost",
    "http://localhost:3000",
]
app.add_middleware(
    CORSMiddleware,
    allow_origins=origins,
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

MODEL_PATH = os.path.join(
    "C:\\Users\\yusuf\\Desktop\\potato-disease\\saved_models", "1")
MODEL = tf.keras.models.load_model(MODEL_PATH)

CLASS_NAMES = ["Early Blight", "Late Blight", "Healthy"]


@app.get("/ping")
async def ping():
    return "Hello, Welcome to potato disease detection ^_^"

'''
def read_file_as_image(data) -> np.ndarray:
    image = np.array(Image.open(BytesIO(data)))
    return image
'''


def read_file_as_image(data, target_size=(256, 256)) -> np.ndarray:
    img = Image.open(BytesIO(data)).resize(target_size)
    # Convert image to NumPy array
    image_array = np.array(img)

    return image_array


# This will be a file sent by mobile app or web. It will be an image of potato plant leaf
@app.post("/predict")
async def predict(  # Whoever calls this "/predict" method, they HAVE to send a file.
        file: UploadFile = File(...)
):
    image = read_file_as_image(await file.read())
    img_batch = np.expand_dims(image, 0)  # Convert input to mini-batch

    predictions = MODEL.predict(img_batch)

    predicted_class = CLASS_NAMES[np.argmax(predictions[0])]
    confidence = np.max(predictions[0])
    return {
        'class': predicted_class,
        'confidence': float(confidence)
    }


if __name__ == "__main__":
    uvicorn.run(app, host='localhost', port=9000)
