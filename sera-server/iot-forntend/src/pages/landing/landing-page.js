

function LandingPage() {
    return (
        <div>
            <h2>A smart agriculture IoT project, <em>developed by AGU students</em></h2>
            <div id="myCarousel" className="carousel slide container-fluid mx-0 px-0" data-bs-ride="carousel">
                <div className="carousel-indicators">
                    <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="0" className="active" aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="1" aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#myCarousel" data-bs-slide-to="2" aria-label="Slide 3"></button>
                </div>
                <div className="carousel-inner">
                    <div className="carousel-item active ">
                        <img
                            src={`${process.env.PUBLIC_URL}/assets/project-img.jpg`}
                            className="d-block w-100"
                            alt="Environment Sensing"
                        />
                        <div className="container">
                            <div className="carousel-caption d-none d-md-block text-start p-4 rounded-3" style={{ backdropFilter: 'blur(4px)', backgroundColor: 'rgba(40, 40, 40, 0.7)' }}>
                                <h3>Sense the environment</h3>
                                <p className="opacity-75">A greenhouse aware of the environmental conditions like humidity, temperature, ligth density</p>

                            </div>
                        </div>
                    </div>
                    <div className="carousel-item ">
                        <img
                            src={`${process.env.PUBLIC_URL}/assets/action-img.png`}
                            className="d-block w-100"
                            alt="Taking Actions"
                        />
                        <div className="container">
                            <div className="carousel-caption d-none d-md-block text-center rounded-3" style={{ backdropFilter: 'blur(4px)', backgroundColor: 'rgba(40, 40, 40, 0.7)' }}>
                                <h3>Take immediate action</h3>
                                <p>Based on the optimal conditions, it takes the required actions automatically.</p>
                            </div>
                        </div>
                    </div>
                    <div className="carousel-item ">
                        <img
                            src={`${process.env.PUBLIC_URL}/assets/ml-img.png`}
                            className="d-block w-100"
                            alt="ML track"
                        />
                        <div className="container">
                            <div className="carousel-caption d-none d-md-block text-start p-4 rounded-3" style={{ backdropFilter: 'blur(4px)', backgroundColor: 'rgba(40, 40, 40, 0.7)' }}>
                                <h3>ML keeps track of health</h3>
                                <p>Our Machine Learning model keeps track of the health condition of the plants for a specific disease.</p>
                            </div>
                        </div>
                    </div>
                </div>
                <button className="carousel-control-prev" type="button" data-bs-target="#myCarousel" data-bs-slide="prev">
                    <span className="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span className="visually-hidden">Previous</span>
                </button>
                <button className="carousel-control-next" type="button" data-bs-target="#myCarousel" data-bs-slide="next">
                    <span className="carousel-control-next-icon" aria-hidden="true"></span>
                    <span className="visually-hidden">Next</span>
                </button>
            </div>

            <div className="container">
                
                <h2>Our Team</h2>

                <div class="row featurette">
                    <div class="col-md-7">
                        <h2 class="featurette-heading fw-normal lh-1">First featurette heading. <span class="text-body-secondary">It’ll blow your mind.</span></h2>
                        <p class="lead">Some great placeholder content for the first featurette here. Imagine some exciting prose here.</p>
                    </div>
                    <div class="col-md-5">
                        <svg class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="500" height="500" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 500x500" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="var(--bs-secondary-bg)" /><text x="50%" y="50%" fill="var(--bs-secondary-color)" dy=".3em">500x500</text></svg>
                    </div>
                </div>

                <hr class="featurette-divider"/>

                    <div class="row featurette">
                        <div class="col-md-7 order-md-2">
                            <h2 class="featurette-heading fw-normal lh-1">Oh yeah, it’s that good. <span class="text-body-secondary">See for yourself.</span></h2>
                            <p class="lead">Another featurette? Of course. More placeholder content here to give you an idea of how this layout would work with some actual real-world content in place.</p>
                        </div>
                        <div class="col-md-5 order-md-1">
                            <svg class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="500" height="500" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 500x500" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="var(--bs-secondary-bg)" /><text x="50%" y="50%" fill="var(--bs-secondary-color)" dy=".3em">500x500</text></svg>
                        </div>
                    </div>

                    <hr class="featurette-divider"/>

                        <div class="row featurette">
                            <div class="col-md-7">
                                <h2 class="featurette-heading fw-normal lh-1">First featurette heading. <span class="text-body-secondary">It’ll blow your mind.</span></h2>
                                <p class="lead">Some great placeholder content for the first featurette here. Imagine some exciting prose here.</p>
                            </div>
                            <div class="col-md-5">
                                <svg class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="500" height="500" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 500x500" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="var(--bs-secondary-bg)" /><text x="50%" y="50%" fill="var(--bs-secondary-color)" dy=".3em">500x500</text></svg>
                            </div>
                        </div>

                        <hr class="featurette-divider"/>

                            <div class="row featurette">
                                <div class="col-md-7 order-md-2">
                                    <h2 class="featurette-heading fw-normal lh-1">Oh yeah, it’s that good. <span class="text-body-secondary">See for yourself.</span></h2>
                                    <p class="lead">Another featurette? Of course. More placeholder content here to give you an idea of how this layout would work with some actual real-world content in place.</p>
                                </div>
                                <div class="col-md-5 order-md-1">
                                    <svg class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="500" height="500" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 500x500" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="var(--bs-secondary-bg)" /><text x="50%" y="50%" fill="var(--bs-secondary-color)" dy=".3em">500x500</text></svg>
                                </div>
                            </div>

                            <hr class="featurette-divider"/>

                            </div>

                        </div>
                        );
}

                        export default LandingPage;