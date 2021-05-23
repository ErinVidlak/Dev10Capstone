function Errors({ errors }) {
    if (errors.length === 0) {
        return null;
    } 

    return (
        <div className="card">
            <div className="card-content">
                <span className="card-title">The following errors were found:</span>
                <ul>
                {errors.map(error => (
                    <li className="collection-item" key={error}>{error}</li>
                ))}
                </ul>
            </div>
        </div>
    )
} 

export default Errors;