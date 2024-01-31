import { useSelector } from "react-redux";
import { useAppDispatch } from "../../app/hooks";
import { clearError, loginAsync } from "../../features/auth/authenticationSlice";
import { useNavigate } from 'react-router-dom';
import { useEffect, useState } from "react";
import Spinner from "../../components/spinner";
import Alert from "../../components/alert";

function LoginPage() {

    const navigate = useNavigate();

    const requestStatus = useSelector((state) => state.auth.status);
    const token = useSelector((state) => state.auth.jwtToken);
    const error = useSelector((state) => state.auth.error);
    const isLogin = useSelector((state) => state.auth.isLogin);

    const dispatch = useAppDispatch();

    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    useEffect(() => {
        if (isLogin) {
            navigate('/reports');
        }
    }, [isLogin]);

    const handleUsernameChange = (event) => {
        setUsername(event.target.value);
    };

    const handlePasswordChange = (event) => {
        setPassword(event.target.value);
    };

    const handleSubmit = async (event) => {
        event.preventDefault();

        // Perform your login action here using username and password
        // Example: dispatch(loginAction(username, password));
        console.log('Logging in with:', username, password);
        await dispatch(loginAsync({ username, password }));

    };

    const closeModal = () => {
        console.log('Close modal');
        dispatch(clearError());
    };

    return (
        <div className="login-form-wrapper">
            <Spinner loading={requestStatus === 'loading'} />

            <div className="form-signin w-100 m-auto bg-dark shadow-md rounded">
                <form onSubmit={handleSubmit}>
                    {/* <img className="mb-4" src={`${process.env.PUBLIC_URL}/assets/login-logo.png`} alt="login logo" width="80" height="72" /> */}
                    <h1 className="h3 mb-3 fw-normal text-white">Please Login</h1>

                    <div className="form-floating">
                        <input value={username} onChange={handleUsernameChange} type="text" className="form-control" id="floatingInput" placeholder="example-username" />
                        <label htmlFor="floatingInput">Username</label>
                    </div>

                    <br></br>

                    <div className="form-floating">
                        <input value={password} onChange={handlePasswordChange} type="password" className="form-control" id="floatingPassword" placeholder="Password" />
                        <label htmlFor="floatingPassword">Password</label>
                    </div>

                    <br></br>

                    <button className="btn btn-primary w-100 py-2" type="submit">Login</button>

                    {/* <p className="mt-5 mb-3 text-body-secondary">&copy; 2024</p> */}
                    {error && (
                        <>
                            <hr></hr>
                            <Alert error={error} closeModal={closeModal} />
                        </>
                    )}
                </form>
            </div>
        </div>
    );
}

export default LoginPage;