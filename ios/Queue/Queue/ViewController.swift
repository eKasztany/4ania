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

    @IBOutlet weak var tableView: UITableView!
    @IBOutlet weak var mapView: MKMapView!
    var state: State = .loading {
        didSet {
            switch state {
            case .loaded(let departments):
                loadAnnotations(annotations: departments)
                tableView.reloadData()
            default:
                break
            }
        }
    }

    override func viewDidLoad() {
        super.viewDidLoad()

        mapView.delegate = self
        tableView.dataSource = self

        FirebaseClient().load(resource: Department.all) { result in
            if let result = result {
                self.state = .loaded(result)
            } else {
                self.state = .error
            }
            print(result)
        }

        RemoteNotificationsManager.register { (result) in
        }
    }

    private func loadAnnotations(annotations: [MKAnnotation]) {
        for annotation in annotations {
            mapView.addAnnotation(annotation)
        }
        mapView.showAnnotations(annotations, animated: true)
    }
}

extension ViewController: UITableViewDataSource {
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        switch state {
        case .loaded(let departments):
            let cell = tableView.dequeueReusableCell(withIdentifier: "departmentCell", for: indexPath) as UITableViewCell
            cell.textLabel?.text = departments[indexPath.row].name
            return cell
        default:
            break
        }
        return UITableViewCell()
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        switch state {
        case .loaded(let departments):
            return departments.count
        default:
            return 1
        }
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
