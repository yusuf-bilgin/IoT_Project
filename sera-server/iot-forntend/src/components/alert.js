

function Alert({ error, closeModal}) {

    return (
        <div className="alert alert-danger d-flex justify-content-center" role="alert">
            <p>{error}</p>
            <button onClick={closeModal} type="button" className="btn-close" aria-label="Close"></button>
        </div>
    );
}

export default Alert;