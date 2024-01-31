

function Spinner({ loading}) {

    return (
        <>
        {loading && (
            <div className="spinner-overlay">
              <div className="spinner-border" role="status">
                <span className="visually-hidden">Loading...</span>
              </div>
            </div>
          )}
        </>
    );
}

export default Spinner;