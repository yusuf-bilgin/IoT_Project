
import { Route, Navigate } from 'react-router-dom';
import { useAppSelector } from '../app/hooks';

const PrivateRoute = ({ element }) => {
    
    const isLogin = useAppSelector((state) => state.auth.isLogin);

    return isLogin ? element : <Navigate to="/login" />;
};

export default PrivateRoute;