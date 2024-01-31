import React, { useCallback, useState } from "react";
import { useDropzone } from "react-dropzone";
import { useAppDispatch, useAppSelector } from "../app/hooks";
import {
  clearData,
  clearError,
  predictImageHealth,
} from "../features/ai/aiSlice";
import Alert from "./alert";
import Spinner from "./spinner";

const UploadComponent = () => {
  const result = useAppSelector((state) => state.ai.result);
  const status = useAppSelector((state) => state.ai.loading);
  const error = useAppSelector((state) => state.ai.error);
  const dispatch = useAppDispatch();

  const [file, setFile] = useState(null);

  const onDrop = useCallback((acceptedFiles) => {
    setFile(acceptedFiles[0]);
  }, []);

  const handleSubmit = () => {
    if (file) {
      const formData = new FormData();
      formData.append("file", file);
      dispatch(predictImageHealth(formData));
    }
  };

  const closeModal = () => {
    dispatch(clearError());
  };

  const clearFile = () => {
    setFile(null);
    dispatch(clearData());
  };

  const { getRootProps, getInputProps, isDragActive } = useDropzone({ onDrop });

  return (
    <div className="upload-container">
      <div className="row justify-content-center">
        <div className="col-sm-12 col-md-8 col-lg-6 p-2 border shadow-md rounded upload-box">
          <h4>Upload Image</h4>
          <hr />
          <div
            {...getRootProps()}
            className={`dropzone ${isDragActive ? "active" : ""}`}
          >
            <input {...getInputProps()} />
            <p className="h-100 mb-0">
              Drag & drop your image here, or click to select one
            </p>
          </div>
          {file && (
            <div className="mt-3">
              <p>Selected file: {file.name}</p>
              <img
                src={URL.createObjectURL(file)}
                alt="selected"
                width="200"
                className="selected-image"
              />
            </div>
          )}
        </div>
      </div>

      <div className="buttons-container">
        {file && (
          <button className="btn btn-danger me-1" onClick={clearFile}>
            Clear
          </button>
        )}

        <button
          className="btn btn-primary"
          onClick={handleSubmit}
          disabled={!file}
        >
          Send
        </button>
      </div>

      {error && (
        <div className="row mt-1 justify-content-center">
          <div className="col-12 col-md-8 col-lg-6 p-2">
            <Alert error={error} closeModal={closeModal} />
          </div>
        </div>
      )}
      {status === "loading" && <Spinner />}
      {result && <ResultTable result={result} />}
    </div>
  );
};

function ResultTable({ result }) {
  return (
    <div className="mt-3 container result-container">
      <h4>Result</h4>
      <table className="table">
        <thead>
          <tr>
            <th>Class</th>
            <th>Confidence</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>{result.class}</td>
            <td>{result.confidence}</td>
          </tr>
        </tbody>
      </table>
    </div>
  );
}

export default UploadComponent;
