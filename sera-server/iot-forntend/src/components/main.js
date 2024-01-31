import {
    Routes,
    Route
} from "react-router-dom";
import AI from "../pages/ai-page/ai";
import Reports from "../pages/reports/reports";
import NotFound from "../pages/not-found";
import LandingPage from "../pages/landing/landing-page";
import LoginPage from "../pages/login/login-page";
import PrivateRoute from "./private-path";


function Main() {

    return (

        <main className="flex-shrink-0">
            <div className="container">
                <Routes>
                    <Route path="/" element={<LandingPage />} />
                    <Route path="/login" element={<LoginPage />} />

                    <Route path="/reports" element={<PrivateRoute element={<Reports />} />} />
                    <Route path="/ai" element={<PrivateRoute element={<AI />} />} />
                    {/* <Route path="/reports" element={<Reports />} />
                    <Route path="/ai" element={<AI />} /> */}
                    {/* Add more routes as needed */}
                    <Route path="*" element={<NotFound />} />
                </Routes>
            </div>
        </main>
    );
}

export default Main;