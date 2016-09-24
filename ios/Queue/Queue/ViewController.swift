//
//  Created by Aleksander Grzyb on 24/09/16.
//  Copyright © 2016 Aleksander Grzyb. All rights reserved.
//

import UIKit
import MapKit

class ViewController: UIViewController {

    enum State {
        case loading
        case loaded([Department])
        case empty
        case error
    }

    @IBOutlet weak var mapView: MKMapView!
    var state: State = .loading {
        didSet {
            switch state {
            case .loaded(let deparments):
                loadAnnotations(annotations: deparments)
            default:
                break
            }
        }
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        mapView.delegate = self

        FirebaseClient().load(resource: Department.all) { result in
            if let result = result {
                self.state = .loaded(result)
            } else {
                self.state = .error
            }
            print(result)
        }

        RemoteNotificationsManager.register { (result) in
            print(result)
        }
    }

    private func loadAnnotations(annotations: [MKAnnotation]) {
        for annotation in annotations {
            mapView.addAnnotation(annotation)
        }
        mapView.showAnnotations(annotations, animated: true)
    }
}

extension ViewController: MKMapViewDelegate {
    func mapView(_ mapView: MKMapView, viewFor annotation: MKAnnotation) -> MKAnnotationView? {
        let newAnnotation = MKPinAnnotationView(annotation: annotation, reuseIdentifier: "pinAnnotation")
        newAnnotation.canShowCallout = true
        newAnnotation.rightCalloutAccessoryView = UIButton(type: .detailDisclosure)
        return newAnnotation
    }
}
